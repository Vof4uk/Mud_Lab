package ua.mykytenko.serviceTest.jpa;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import ua.mykytenko.Profiles;
import ua.mykytenko.serviceTest.BasicPowderServiceTest;

@ActiveProfiles(profiles = {Profiles.JPA, Profiles.POSTGRES})
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class JpaBasicPowderServiceTestingTest extends BasicPowderServiceTest{
    {
        startSequence = 10000015 - 1;
    }
}
