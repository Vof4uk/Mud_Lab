package ua.mykytenko;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.mykytenko.entities.BaseEntity;

import java.util.List;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:springTest-app.xml")
public class AbstractTest {

    protected List entities;
    protected int startSequence = 0;

    @Before
    public void beforeTest(){
        for (int i = 0; i < entities.size(); i++) {
            ((BaseEntity)entities.get(i)).setId(startSequence + i + 1);
        }
    }
}
