package ua.mykytenko.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.mykytenko.AbstractTest;
import ua.mykytenko.entities.samples.Sample;
import ua.mykytenko.entities.samples.SampleFamily;
import ua.mykytenko.util.exceptions.ForbiddenRequestException;
import ua.mykytenko.util.exceptions.NotFoundException;
import ua.mykytenko.web.controllers.sample.SampleFamilyController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static ua.mykytenko.SampleFamilyTestData.*;

/**
 * Created by Микитенко on 13.11.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-app.xml")
public class InMemoryMockSampleFamilyControllerTest extends AbstractTest{

    {
        entities = FAMILIES;
    }

    @Autowired
    SampleFamilyController controller;

    @Test
    public void testCreateAndDelete(){
        List<Integer> listNotSaved = new ArrayList<>();
        List<Integer> listNotDeleted = new ArrayList<>();
        List<Integer> listNotGet = new ArrayList<>();

        int initialId = 9000;
        int count = 1;
        for (int i = 1; i <= 5; i++) {
            try {
                controller.create(new SampleFamily("" + count, initialId + 1000 * count), i);
            } catch (ForbiddenRequestException e) {
                listNotSaved.add(i);
                count++;
            }
        }
        for (int i = 1; i <= 5; i++) {
            try {
                controller.delete(3 + count, i);
                controller.get(3 + count, i);
            } catch (ForbiddenRequestException e) {
                listNotDeleted.add(i);
            }catch (NotFoundException e) {
                listNotGet.add(i);
            }
        }
        assertEquals(Arrays.asList(1, 2, 4, 5), listNotSaved);
        assertEquals(Arrays.asList(1, 2, 4, 5), listNotDeleted);
        assertEquals(Arrays.asList(3), listNotGet);


    }

    @Test
    public void testGet(){
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            try {
                SampleFamily family = controller.get(1,i);
                assertEquals(family, FAMILIES.get(0));
            }catch (ForbiddenRequestException ex){
                list.add(i);
            }
        }
        assertEquals(Arrays.asList(new Integer[]{1, 5}), list);
    }

//    @Test
//    public void testUpdate(){
//        List<Integer> listNotUpd = new ArrayList<>();
//        for (int i = 1; i <= 5; i++) {
//            try {
//                SampleFamily family = controller.(1, i);
//                assertEquals(family, FAMILIES.get(0));
//            }catch (ForbiddenRequestException ex){
//                listNotUpd.add(i);
//            }
//        }
//        assertEquals(Arrays.asList(new Integer[]{1, 5}), listNotUpd);
//    }
}
