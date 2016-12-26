package ua.mykytenko.serviceTest.jdbc;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import ua.mykytenko.Profiles;
import ua.mykytenko.serviceTest.SampleServiceTest;

@ActiveProfiles(profiles = {Profiles.JDBC, Profiles.POSTGRES})
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class JdbcSampleServiceTest extends SampleServiceTest{
    {
        startSequence = 200000 - 1;
    }
}
