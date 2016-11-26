package ua.mykytenko.serviceTest;

import org.junit.Ignore;
import ua.mykytenko.entities.BaseEntity;
import ua.mykytenko.entities.tests.AbstractTesting;
import ua.mykytenko.service.TestingService;
import ua.mykytenko.to.TestingTo;
import ua.mykytenko.util.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
/**
 * Created by Микитенко on 26.11.2016.
 */
@Ignore
public class AbstractTestingServiceTest extends AbstractCrudServiceTest {

    protected TestingTo newTO;
    protected List<TestingTo> transferObjects;

    @Override
    public void testGet() {
        assertEquals(transferObjects.get(0),service.get(1));
    }

    @Override
    public void testDelete() {
        List<TestingTo> l = service.getAll();
        assertNotNull(service);
        int id = Integer.valueOf(((TestingTo)service.saveNew(newEntity)).getCore().get("id"));
        thrown.expect(NotFoundException.class);
        service.delete(id);
        service.get(id);
    }

    @Override
    public void testSaveNew() {
        List<TestingTo> l = service.getAll();
        newEntity.setId(null);
        TestingTo e = (TestingTo) service.saveNew(newEntity);
        newEntity.setId(Integer.valueOf(e.getCore().get("id")));
        assertEquals(new TestingTo((AbstractTesting)newEntity), e);
        service.delete(newEntity.getId());
    }

    @Override
    public void testGetAll() {
        List<TestingTo> l = service.getAll();
        assertEquals(service.getAll(), transferObjects);
    }

    @Override
    public void testUpdate() {
        newEntity.setId(1);
        service.update(clazz.cast(newEntity));
        assertEquals(new TestingTo((AbstractTesting)newEntity), service.get(1));
        service.update(entities.get(0));
    }


    public void testGetAllBySampleId(){
        //TODO
    }
    public void testGetParameterOrder(){
        //TODO
    }
    public void testSetParameterOrder(){
        //TODO
    }


}
