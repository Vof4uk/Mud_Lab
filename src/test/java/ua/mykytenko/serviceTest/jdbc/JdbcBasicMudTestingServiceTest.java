package ua.mykytenko.serviceTest.jdbc;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import ua.mykytenko.Profiles;
import ua.mykytenko.serviceTest.BasicMudServiceTestingTest;

@ActiveProfiles(profiles = {Profiles.JDBC, Profiles.POSTGRES})
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class JdbcBasicMudTestingServiceTest extends BasicMudServiceTestingTest{
    {
        startSequence = 10000013 - 1;
    }
}
