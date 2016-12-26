package ua.mykytenko;

import ua.mykytenko.entities.tests.solutions.BasicMudTesting;
import ua.mykytenko.to.TestingTo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasicMudTestingData {
    public static final TestingTo TO_1;
    public static final TestingTo TO_2;
    public static final List<TestingTo> TOS = new ArrayList<>();
    public static final TestingTo NEW_TO;
//    public static final TestingTo COMPARE_TO;

    public static final BasicMudTesting TEST_1;
    public static final BasicMudTesting TEST_2;
    public static final List<BasicMudTesting> TESTS = new ArrayList<>();
    public static final BasicMudTesting NEW_TEST;

    static{
        Map<Integer, Integer> gels1 = new HashMap<>();
        gels1.put(10, 4);gels1.put(60, 8);gels1.put(600, 15);
        Map<Integer, Integer> gels2 = new HashMap<>();
        gels2.put(10, 5);gels2.put(60, 10);gels2.put(600, 21);

        Map<Integer, Integer> rheo1 = new HashMap<>();
        rheo1.put(600, 45);rheo1.put(300, 31);rheo1.put(200, 25);rheo1.put(100, 19);rheo1.put(6, 5);rheo1.put(3, 4);
        Map<Integer, Integer> rheo2 = new HashMap<>();
        rheo2.put(600, 33);rheo2.put(300, 21);rheo2.put(200, 12);rheo2.put(100, 10);rheo2.put(6, 3);rheo2.put(3, 2);

        TEST_1 = new BasicMudTesting(0.5f, 1.03f, 24, 10.1f, 8.9f, rheo1, gels2);
        TEST_2 = new BasicMudTesting(2f, 1.03f, 16, 15f, 7.2f, rheo2, gels1);
        NEW_TEST = new BasicMudTesting(2.1f, 1.03f, 10, 14f, 7.8f, rheo1, gels1);
        TEST_1.setSampleId(200000);
        TEST_2.setSampleId(200001);
        NEW_TEST.setSampleId(200000);
        TEST_1.setId(1);
        TEST_2.setId(2);
        TESTS.add(TEST_1);
        TESTS.add(TEST_2);

        TO_1 = new TestingTo(TEST_1);
        TO_2 = new TestingTo(TEST_2);
        NEW_TO = new TestingTo(NEW_TEST);
        TOS.add(TO_1);
        TOS.add(TO_2);
    }

}
