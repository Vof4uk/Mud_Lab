package ua.mykytenko.serviceTest.jdbc;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import ua.mykytenko.Profiles;
import ua.mykytenko.serviceTest.BasicPowderServiceTest;

@ActiveProfiles(profiles = {Profiles.JDBC, Profiles.POSTGRES})
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class JdbcBasicPowderTestingTest extends BasicPowderServiceTest{
    {
        startSequence = 10000015 - 1;
    }
}
