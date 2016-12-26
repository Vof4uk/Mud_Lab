package ua.mykytenko.serviceTest.jdbc;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import ua.mykytenko.Profiles;
import ua.mykytenko.entities.samples.SampleFamily;
import ua.mykytenko.serviceTest.SampleFamilyServiceTest;

@ActiveProfiles(profiles = {Profiles.JDBC, Profiles.POSTGRES})
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class JdbcSampleFamilyServiceTest extends SampleFamilyServiceTest{
    {
        startSequence = 10000000 - 1;
    }

//    @Override
//    public void beforeTest() {
//        super.beforeTest();
//        int currentId = ((SampleFamily)newEntity).getInitialId();
//        ((SampleFamily)newEntity).setCurrentId(currentId);
//    }
}
