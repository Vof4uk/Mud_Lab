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
import org.springframework.transaction.annotation.Transactional;
import ua.mykytenko.Profiles;
import ua.mykytenko.entities.samples.Sample;
import ua.mykytenko.entities.samples.SampleFamily;
import ua.mykytenko.repository.SampleFamilyRepository;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Profile(Profiles.JDBC)
@Repository
public class JdbcSampleFamilyRepositoryImpl implements SampleFamilyRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SimpleJdbcInsert insertSF;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.insertSF = new SimpleJdbcInsert(dataSource)
                .withTableName("sample_families")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public List<SampleFamily> getAll() {
        return namedParameterJdbcTemplate.query(
                "SELECT id, name, initial_family_id, current_family_id " +
                        "FROM sample_families;",
                new SFamilyMapper());
    }

    @Override
    public SampleFamily get(int id) {
        String sql = "SELECT id, name, initial_family_id, current_family_id FROM sample_families WHERE id = " + id;
        Optional<SampleFamily> sf = this.jdbcTemplate.query(sql, new SFamilyMapper())
                .stream().findFirst();
        return sf.isPresent()? sf.get() : null;
    }

    @Override
    public SampleFamily save(SampleFamily family) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("name", family.getName())
                .addValue("initial_family_id", family.getInitialId())
                .addValue("id", family.getId())
                .addValue("current_family_id", family.getInitialId());
        if(family.isNew())
        {
            int id = insertSF.executeAndReturnKey(map).intValue();
            family.setId(id);
        }
        else
        {
            int n = namedParameterJdbcTemplate.update(
                            "UPDATE sample_families " +
                                    "SET name=:name, initial_family_id=:initial_family_id, current_family_id=:current_family_id " +
                                    "WHERE id=:id", map);
            if(n == 0) return null;
                //TODO think about restrict edit current_id
        }
        return family;
    }

    @Override
    public Integer pullId(String familyName) {
        String sql = "SELECT current_family_id FROM sample_families WHERE name = '" + familyName + "';";
        return this.jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public Integer peakId(String familyName) {
        jdbcTemplate.execute("BEGIN;");
        int n = this.pullId(familyName)  + 1;
        String sql = "UPDATE sample_families SET current_family_id =" + n + " WHERE sample_families.name = '" + familyName + "';";
        jdbcTemplate.execute(sql);
        jdbcTemplate.execute("COMMIT;");
        return n;
    }

    @Override
    public boolean delete(int id) {
        int n = jdbcTemplate.update("DELETE FROM sample_families WHERE id = " + id);
        return n > 0;
    }

    private static final class SFamilyMapper implements RowMapper<SampleFamily>{
        @Override
        public SampleFamily mapRow(ResultSet rs, int rowNum) throws SQLException {
            SampleFamily sf = new SampleFamily();
            sf.setName(rs.getString("name"));
            sf.setId(rs.getInt("id"));
            sf.setInitialId(rs.getInt("initial_family_id"));
            sf.setCurrentId(rs.getInt("current_family_id"));
            return sf;
        }
    }
}
