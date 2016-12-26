package ua.mykytenko.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ua.mykytenko.Profiles;
import ua.mykytenko.entities.tests.solutions.BasicMudTesting;
import ua.mykytenko.repository.TestingRepository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@Profile(Profiles.JDBC)
public class JdbcBasicMudTestingRepositoryImpl implements TestingRepository<BasicMudTesting> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SimpleJdbcInsert insertMudTesting;
    private SimpleJdbcInsert insertRheology;
    private SimpleJdbcInsert insertGels;

    @Autowired
    public void setDatasource(DataSource datasource){
        insertMudTesting = new SimpleJdbcInsert(datasource)
                .withTableName("basic_mud_tests")
                .usingGeneratedKeyColumns("id");
        insertRheology = new SimpleJdbcInsert(datasource)
                .withTableName("rheology");
        insertGels = new SimpleJdbcInsert(datasource)
                .withTableName("gels");

    }

    private static final String SQL_GET_ALL =
            "SELECT basic_mud_tests.id, sample_id, ph, mudweight, viscosity, fluid_loss, sample_content" +
            ", rheology.temp rheology_temp" +
            ", rpm600, rpm300, rpm200, rpm100, rpm60, rpm30, rpm6, rpm3" +
            ", gels.temp gels_temp, after10s, after60s, after600s" +
            " FROM basic_mud_tests" +
            " LEFT OUTER JOIN rheology ON rheology.test_id=id" +
            " LEFT OUTER JOIN gels ON gels.test_id=id";


    @Override
    public BasicMudTesting get(int id) {
        Optional<BasicMudTesting> t = namedParameterJdbcTemplate.query(SQL_GET_ALL +
                        " WHERE basic_mud_tests.id = " + id + ";", new BasicMudMapper())
                .stream().findFirst();
        return t.isPresent()? t.get() : null;
    }

    @Override
    public boolean delete(int id) {
        int count = jdbcTemplate.update("DELETE FROM basic_mud_tests WHERE id=?", id);
        return count > 0;
    }

    @Override
    public List<BasicMudTesting> getAll() {
        return namedParameterJdbcTemplate.query(SQL_GET_ALL, new BasicMudMapper());
    }

    @Override
    public BasicMudTesting save(BasicMudTesting test) {
        MapSqlParameterSource testSource = new MapSqlParameterSource()
                .addValue("sample_id", test.getSampleId(), Types.INTEGER)
                .addValue("ph", test.getpH(), Types.DOUBLE)
                .addValue("mudweight", test.getMudWeight(), Types.DOUBLE)
                .addValue("viscosity", test.getViscosity(), Types.INTEGER)
                .addValue("fluid_loss", test.getFluidLoss(), Types.DOUBLE)
                .addValue("sample_content", test.getSampleContent(), Types.DOUBLE)
                .addValue("entity_name", test.getEntityName());

        MapSqlParameterSource rheo = test.getRheology().entrySet().stream()
                .collect(MapSqlParameterSource::new, (map, entry) -> map.addValue("rpm" + entry.getKey().toString(), entry.getValue()), (a, b)->{});
        MapSqlParameterSource gels = test.getGels().entrySet().stream()
                .collect(MapSqlParameterSource::new, (map, entry) -> map.addValue("after" + entry.getKey().toString() + "s", entry.getValue()), (a, b)->{});

        int id;

        if(test.isNew())
        {
            id = insertMudTesting.executeAndReturnKey(testSource).intValue();
            test.setId(id);
        }
        else
        {
            id = test.getId();
            testSource.addValue("id", test.getId());
            int count;
            try {
                jdbcTemplate.update("DELETE FROM rheology WHERE test_id=?;", id);
                jdbcTemplate.update("DELETE FROM gels WHERE test_id=?;", id);
                count = namedParameterJdbcTemplate.update("UPDATE basic_mud_tests SET ph=:ph" +
                        ", mudweight=:mudweight, viscosity=:viscosity" +
                        ", fluid_loss=:fluid_loss, sample_content=:sample_content" +
                        " WHERE id=:id;", testSource);
            } catch (DataAccessException e) {
                e.printStackTrace();
                return null;
            }
            if(count <= 0) return null;
        }
        gels.addValue("test_id", id);
        rheo.addValue("test_id", id);
        insertGels.execute(gels);
        insertRheology.execute(rheo);
        return test;
    }

    @Override
    public List<BasicMudTesting> getAllBySampleId(int sampleId) {
        return namedParameterJdbcTemplate.query(SQL_GET_ALL +
                " WHERE sample_id = " + sampleId + ";"
                , new BasicMudMapper());
    }

    @Override
    public boolean setParametersList(String name, List<String> paramList) {
        return false;
    }

    @Override
    public List<String> getParametersList(String name) {
        return null;
    }

    private static final class BasicMudMapper implements RowMapper<BasicMudTesting>{
        @Override
        public BasicMudTesting mapRow(ResultSet rs, int rowNum) throws SQLException {
            BasicMudTesting testing = new BasicMudTesting();
            testing.setId(rs.getInt("id"));
            testing.setSampleId(rs.getInt("sample_id"));
            testing.setpH(rs.getFloat("ph"));
            testing.setFluidLoss(rs.getFloat("fluid_loss"));
            testing.setViscosity(rs.getInt("viscosity"));
            testing.setMudWeight(rs.getFloat("mudweight"));
            testing.setSampleContent(rs.getFloat("sample_content"));

            Map<Integer, Integer> gels = new HashMap<>();
            gels.put(10,rs.getInt("after10s"));
            gels.put(60,rs.getInt("after60s"));
            gels.put(600,rs.getInt("after600s"));
            testing.setGels(gels);

            Map<Integer, Integer> rheo = new HashMap<>();
            rheo.put(3, rs.getInt("rpm3"));
            rheo.put(6, rs.getInt("rpm6"));
            rheo.put(30, rs.getInt("rpm30"));
            rheo.put(60, rs.getInt("rpm60"));
            rheo.put(100, rs.getInt("rpm100"));
            rheo.put(200, rs.getInt("rpm200"));
            rheo.put(300, rs.getInt("rpm300"));
            rheo.put(600, rs.getInt("rpm600"));
            testing.setRheology(rheo);


            return testing;
        }
    }
}

