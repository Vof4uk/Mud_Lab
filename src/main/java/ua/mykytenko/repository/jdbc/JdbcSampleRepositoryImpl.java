package ua.mykytenko.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ua.mykytenko.Profiles;
import ua.mykytenko.entities.samples.Sample;
import ua.mykytenko.repository.SampleFamilyRepository;
import ua.mykytenko.repository.SampleRepository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static ua.mykytenko.entities.samples.Description.*;

@Repository
@Profile(Profiles.JDBC)
public class JdbcSampleRepositoryImpl implements SampleRepository{
    @Autowired
    private SampleFamilyRepository families;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SimpleJdbcInsert simpleJdbcInsert;

    private static final String SQL_GET_ALL_SAMPLES =
                    "SELECT samples.id , samples.name \"sample_name\", sample_families.id \"family_id\", " +
                    "samples.id_in_family, samples.notes, samples.arrived, samples.weight," +
                    "samples.manufacturer_id, man.name \"manufacturer\", samples.vendor_id, ven.name \"vendor\", " +
                    "string_agg(DISTINCT sample_applications.name, ', ') \"application\", " +
                    "string_agg(DISTINCT concat(to_char( sample_components.percent, '999D9'), '% - ', sample_components.name) ,'; ') \"components\"" +
                    "FROM samples " +
                    "LEFT OUTER JOIN sample_families ON samples.family_id=sample_families.id " +
                    "LEFT OUTER JOIN companies \"ven\"  ON samples.vendor_id = ven.id " +
                    "LEFT OUTER JOIN companies \"man\" ON samples.manufacturer_id = man.id " +
                    "LEFT OUTER JOIN sample_applications ON samples.id = sample_applications.sample_id " +
                    "LEFT OUTER JOIN sample_components ON samples.id = sample_components.sample_id ";
    private static final String SQL_GROUP_BY = " GROUP BY samples.id, sample_families.id, manufacturer, vendor";

    @Autowired
    public void setDataSource(DataSource dataSource){
        simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("samples")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public List<Sample> getAll() {
        return jdbcTemplate.query(SQL_GET_ALL_SAMPLES + SQL_GROUP_BY + ";", new SampleMapper());
    }

    @Override
    public Sample save(Sample sample) {
        MapSqlParameterSource sampleMap = (new MapSqlParameterSource())
                .addValue("id", sample.getId())
                .addValue("id_in_family", families.peakId(sample.getFamily().getName()))
                .addValue("name", sample.getName())
                .addValue("weight", sample.getWeight())
                .addValue("manufacturer_id", sample.getManufacturer().getId())
                .addValue("vendor_id", sample.getVendor().getId())
                .addValue("arrived", sample.getArrived(), Types.DATE)
                .addValue("family_id", sample.getFamily().getId())
                .addValue("notes", sample.getNotes());

        if(sample.isNew()){
            int sampleId = simpleJdbcInsert.executeAndReturnKey(sampleMap).intValue();
            sample.setId(sampleId);
        }
        else{
            int  n;
            try {
                String sql = "UPDATE samples SET id_in_family=:id_in_family" +
                        ", family_id=:family_id" +
                        ", name=:name, weight=:weight, manufacturer_id=:manufacturer_id" +
                        ", vendor_id=:vendor_id" +
                        ", arrived=:arrived, notes=:notes" +
                        " WHERE id=:id;";
                n = namedParameterJdbcTemplate.update(sql, sampleMap);
            } catch (DataAccessException e) {
                e.printStackTrace();
                return null;
            }
            if (n == 0) return null;
        }
        int sampleId = sample.getId();
        cleanApplications(sampleId);
        cleanCompositions(sampleId);

        SqlParameterSource[] batch = getApplicationBatch(sample);
        String sql = "INSERT INTO sample_applications (sample_id, name) " +
                "VALUES (:sample_id, :application);";
        int[] insertedApplications = namedParameterJdbcTemplate.batchUpdate(sql, batch);

        batch = getCompositionBatch(sample);
        sql = "INSERT INTO sample_components (sample_id, name, percent) " +
                "VALUES (:sample_id, :component_name, :percent);";
        int[] insetedComposition = namedParameterJdbcTemplate.batchUpdate(sql, batch);
        return sample;
    }

    private void cleanApplications(int sampleId){
        String sql = "DELETE from sample_applications WHERE sample_id = " + sampleId;
        jdbcTemplate.update(sql);
    }

    private void cleanCompositions(int sampleId){
        String sql = "DELETE from sample_components WHERE sample_id = " + sampleId;
        jdbcTemplate.update(sql);
    }

    @Override
    public boolean delete(int sampleId) {
        int deleted = jdbcTemplate.update("DELETE FROM samples WHERE samples.id = ?", sampleId);
        return deleted > 0;
    }

    @Override
    public Sample get(int sampleId) {
        String sql = SQL_GET_ALL_SAMPLES + " WHERE samples.id = " +  sampleId + SQL_GROUP_BY + ";";
        Optional<Sample> sample = jdbcTemplate.query(sql, new SampleMapper()).stream().findFirst();
        return sample.isPresent()? sample.get() : null;
    }

    private final class SampleMapper implements RowMapper<Sample>{
        @Override
        public Sample mapRow(ResultSet rs, int rowNum) throws SQLException {
            Sample sample = new Sample();
            sample.setFamilyId(rs.getInt("id_in_family"));
            sample.setNotes(rs.getString("notes"));
            sample.setName(rs.getString("sample_name"));
            sample.setFamily(families.get(rs.getInt("family_id")));
            sample.setArrived(LocalDate.parse(rs.getString("arrived")));
            sample.setWeight(rs.getFloat("weight"));
            sample.setId(rs.getInt("id"));

            sample.setApplications(Applications.parse(rs.getString("application")));

            Company manufacturer = new Company(rs.getString("manufacturer"));
            manufacturer.setId(rs.getInt("manufacturer_id"));
            Company vendor = new Company(rs.getString("vendor"));
            vendor.setId(rs.getInt("vendor_id"));

            sample.setComposition(Components.parse(rs.getString("components")));

            sample.setVendor(vendor);
            sample.setManufacturer(manufacturer);
            return sample;
        }
    }

    private SqlParameterSource[] getApplicationBatch(Sample sample){
        return sample.getApplications().stream()
                .map(app -> (new MapSqlParameterSource()).addValue("sample_id", sample.getId()).addValue("application", app ))
                .toArray(SqlParameterSource[]::new);
    }

    private SqlParameterSource[] getCompositionBatch(final Sample sample){
        return sample.getComposition().entrySet().stream()
                .map(es -> (new MapSqlParameterSource())
                        .addValue("sample_id", sample.getId())
                        .addValue("component_name", es.getKey())
                        .addValue("percent", Float.valueOf(es.getValue())))
                .toArray(SqlParameterSource[]::new);
    }
}
