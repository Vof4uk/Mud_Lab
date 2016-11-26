package ua.mykytenko.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.mykytenko.AbstractTest;
import ua.mykytenko.entities.samples.Sample;
import ua.mykytenko.util.exceptions.ForbiddenRequestException;
import ua.mykytenko.util.exceptions.NotFoundException;
import ua.mykytenko.web.controllers.sample.SampleController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ua.mykytenko.SampleTestData.*;
import static org.junit.Assert.*;

/**
 * Created by Микитенко on 13.11.2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-app.xml")
public class InMemorySampleControllerTest extends AbstractTest{

    {
        entities = SAMPLES;
    }

    @Autowired
    SampleController controller;

    @Test
    public void testGet(){
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            try {
                Sample sample = controller.get(1,i);
                assertEquals(sample, SAMPLES.get(0));
            }catch (ForbiddenRequestException ex){
                list.add(i);
            }
        }
        assertEquals(Arrays.asList(new Integer[]{1, 5}), list);
    }

    @Test
    public void testGetAll(){
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            try{
                controller.getAll(i);
            }catch (ForbiddenRequestException ex){
                list.add(i);
            }
        }
        assertEquals(Arrays.asList(new Integer[]{1, 5}), list);
    }

    @Test
    public void testUpdate(){
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            try {
                controller.update(SAMPLES.get(0),i);
                assertEquals(controller.get(1, 3), SAMPLES.get(0));
            }catch (ForbiddenRequestException ex){
                list.add(i);
            }
        }
        assertEquals(Arrays.asList(new Integer[]{1, 4, 5}), list);
    }

    @Test
    public void testSaveNewAndDelete(){
        List<Integer> listNotSaved = new ArrayList<>();
        List<Integer> listNotDeleted = new ArrayList<>();
        List<Integer> listNotFound = new ArrayList<>();
        int count = 1;
        for (int i = 1; i <= 5; i++) {
            try {
                controller.save(SAMPLES.get(0), i);
                count++;
            } catch (ForbiddenRequestException ex) {
                listNotSaved.add(i);
            }
        }
        for (int i = 1; i <= 5; i++) {
            try {
                controller.delete(count + 2,i);
                controller.get(count + 2, 3);
            }catch (ForbiddenRequestException ex){
                listNotDeleted.add(i);
            }
            catch (NotFoundException ex){
                listNotFound.add(i);
                count--;
            }
        }
        assertEquals(Arrays.asList(new Integer[]{1, 4, 5}), listNotSaved);
        assertEquals(Arrays.asList(new Integer[]{1, 4, 5}), listNotDeleted);
        assertEquals(Arrays.asList(new Integer[]{2, 3}), listNotFound);
    }

    /*@Test
    public void testSave(){
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            try {
                controller.save(SAMPLES.get(0),i);
                assertNotEquals(controller.get(1, 3), SAMPLES.get(0));
            }catch (ForbiddenRequestException ex){
                list.add(i);
            }
        }
        assertEquals(Arrays.asList(new Integer[]{1, 4, 5}), list);
    }*/
}
