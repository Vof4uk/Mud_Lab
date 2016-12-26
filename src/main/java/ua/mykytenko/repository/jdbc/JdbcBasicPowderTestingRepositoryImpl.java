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
import ua.mykytenko.entities.tests.powders.BasicPowderTesting;
import ua.mykytenko.entities.tests.solutions.BasicMudTesting;
import ua.mykytenko.repository.TestingRepository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@Profile(Profiles.JDBC)
public class JdbcBasicPowderTestingRepositoryImpl implements TestingRepository<BasicPowderTesting> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SimpleJdbcInsert insertTesting;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.insertTesting = new SimpleJdbcInsert(dataSource)
                .withTableName("basic_powder_tests")
                .usingGeneratedKeyColumns("id");
    }


    private static final String SQL_GET_ALL = "SELECT id, sample_id, sg, bulk_sg, wetness, appearence, water_soluble FROM basic_powder_tests";

    @Override
    public BasicPowderTesting get(int id) {
        try {
            return jdbcTemplate.queryForObject(SQL_GET_ALL + " WHERE id=?;",new Integer[]{id}, new PowderMapper());
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public boolean delete(int id) {
        return jdbcTemplate.update("DELETE FROM basic_powder_tests WHERE  id=?", id) > 0;
    }

    @Override
    public List<BasicPowderTesting> getAll() {
        return jdbcTemplate.query(SQL_GET_ALL + ";", new PowderMapper());
    }

    @Override
    public BasicPowderTesting save(BasicPowderTesting test) {
        MapSqlParameterSource source = new MapSqlParameterSource()
                .addValue("sample_id", test.getSampleId())
                .addValue("sg", test.getSg())
                .addValue("bulk_sg", test.getBulkSg())
                .addValue("wetness", test.getWetness())
                .addValue("appearence", test.getAppearance())
                .addValue("water_soluble", test.getWaterSoluble());

        if(test.isNew())
        {
            int id = insertTesting.executeAndReturnKey(source).intValue();
            if(id <= 0) return null;
            test.setId(id);
        }
        else
        {
            int count;
            source.addValue("id", test.getId());
            try {
                count = namedParameterJdbcTemplate.update("UPDATE basic_powder_tests" +
                        " SET sample_id=:sample_id, sg=:sg, bulk_sg=:bulk_sg," +
                        " wetness=:wetness, appearence=:appearence, water_soluble=:water_soluble" +
                        " WHERE id=:id;", source);
            } catch (DataAccessException e) {
                return null;
            }

            if(count <= 0) return null;
        }
        return test;
    }

    @Override
    public List<BasicPowderTesting> getAllBySampleId(int sampleId) {
        return jdbcTemplate.query(SQL_GET_ALL + " WHERE sample_id=?;", new PowderMapper(), sampleId);
    }

    @Override
    //TODO
    public boolean setParametersList(String name, List<String> paramList) {
        return false;
    }

    @Override
    //TODO
    public List<String> getParametersList(String name) {
        return null;
    }

    private final class PowderMapper implements RowMapper<BasicPowderTesting>{
        @Override
        public BasicPowderTesting mapRow(ResultSet rs, int rowNum) throws SQLException {
            BasicPowderTesting testing = new BasicPowderTesting();
            testing.setId(rs.getInt("id"));
            testing.setSampleId(rs.getInt("sample_id"));
            testing.setSg(rs.getInt("sg"));
            testing.setBulkSg(rs.getInt("bulk_sg"));
            testing.setWetness(rs.getFloat("wetness"));
            testing.setWaterSoluble(rs.getBoolean("water_soluble"));
            testing.setAppearance(rs.getString("appearence"));
            return testing;
        }
    }
}
