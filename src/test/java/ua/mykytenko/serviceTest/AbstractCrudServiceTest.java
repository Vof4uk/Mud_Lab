package ua.mykytenko.serviceTest;

import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.mykytenko.AbstractTest;
import ua.mykytenko.entities.BaseEntity;
import ua.mykytenko.service.BasicCrudService;
import ua.mykytenko.util.exceptions.MyIllegalStateException;
import ua.mykytenko.util.exceptions.NotFoundException;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Микитенко on 19.11.2016.
 */
@Ignore
public class AbstractCrudServiceTest extends AbstractTest implements ApplicationContextAware{

    protected ApplicationContext springContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        springContext = applicationContext;
    }

    BasicCrudService service;

    protected BaseEntity newEntity;

    protected BaseEntity compareEntity;

    Class clazz;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Ignore
    public void manageEntities(){
        compareEntity = newEntity;
    }

    @Override
    @Before
    public void beforeTest(){
        super.beforeTest();
        newEntity.setId(null);
        manageEntities();
    }

    @Test
    public void testGet(){
        assertEquals(clazz.cast(entities.get(0)), service.get(startSequence + 1));
    }

    @Test
    public void testGetAll(){
        assertEquals(service.getAll(), entities);
    }

    @Test
    public void testDelete(){
        assertNotNull(service);
        int id = ((BaseEntity)service.saveNew(newEntity)).getId();
        thrown.expect(NotFoundException.class);
        service.delete(id);
        service.get(id);
    }

    @Test
    public void testSaveNew(){
        newEntity.setId(null);
        BaseEntity e = (BaseEntity)service.saveNew(newEntity);
        compareEntity.setId(e.getId());
        assertEquals(clazz.cast(compareEntity), service.get(e.getId()));
        service.delete(e.getId());
    }

    @Test
    public void testUpdate(){
        newEntity.setId(1 + startSequence);
        compareEntity.setId(1 + startSequence);
        service.update(clazz.cast(newEntity));
        assertEquals(clazz.cast(compareEntity), service.get(1 + startSequence));
        service.update(entities.get(0));
    }

    @Test
    public void testGetNotFound(){
        thrown.expect(NotFoundException.class);
        service.get(Integer.MAX_VALUE - 1);
    }

    @Test
    public void testSaveNotFound(){
        thrown.expect(MyIllegalStateException.class);
        newEntity.setId(Integer.MAX_VALUE - 1);
        service.saveNew(newEntity);
    }

    @Test
    public void testUpdateNotFound(){
        thrown.expect(NotFoundException.class);
        newEntity.setId(Integer.MAX_VALUE - 1);
        service.update(newEntity);
    }

    @Test
    public void testDeleteNotFound(){
        thrown.expect(NotFoundException.class);
        service.delete(Integer.MAX_VALUE - 1);
    }
}
