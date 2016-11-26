package ua.mykytenko.serviceTest;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
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

    BaseEntity newEntity;

    Class clazz;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Override
    @Before
    public void beforeTest(){
        super.beforeTest();
        newEntity.setId(null);
    }

    @Test
    public void testGet(){
        assertEquals(clazz.cast(entities.get(0)), service.get(1));
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
        newEntity.setId(e.getId());
        assertEquals(clazz.cast(newEntity), service.get(e.getId()));
        service.delete(e.getId());
    }

    @Test
    public void testUpdate(){
        newEntity.setId(1);
        service.update(clazz.cast(newEntity));
        assertEquals(clazz.cast(newEntity), service.get(1));
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
