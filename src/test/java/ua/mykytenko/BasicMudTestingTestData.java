package ua.mykytenko;

import com.sun.org.apache.bcel.internal.generic.NEW;
import ua.mykytenko.entities.tests.solutions.BasicMudTesting;

import java.util.*;

public class BasicMudTestingTestData {
    public static final BasicMudTesting TEST_1;
    public static final BasicMudTesting TEST_2;
    public static final BasicMudTesting NEW_TEST;
    public static final List<BasicMudTesting> TESTS;
    public static final Map<Integer, Integer> GELS_1;
    public static final Map<Integer, Integer> GELS_2;
    public static final Map<Integer, Integer> RHEOLOGY_1;
    public static final Map<Integer, Integer> RHEOLOGY_2;



    static {
        GELS_1 = new HashMap<>();
        GELS_1.put(10, 4);GELS_1.put(60, 8);GELS_1.put(600, 15);
        GELS_2 = new HashMap<>();
        GELS_2.put(10, 5);GELS_2.put(60, 10);GELS_2.put(600, 21);

        RHEOLOGY_1 = new HashMap<>();
        RHEOLOGY_1.put(600, 45);RHEOLOGY_1.put(300, 31);RHEOLOGY_1.put(200, 25);RHEOLOGY_1.put(100, 19);RHEOLOGY_1.put(6, 5);RHEOLOGY_1.put(3, 4);
        RHEOLOGY_2 = new HashMap<>();
        RHEOLOGY_2.put(600, 33);RHEOLOGY_2.put(300, 21);RHEOLOGY_2.put(200, 12);RHEOLOGY_2.put(100, 10);RHEOLOGY_2.put(6, 3);RHEOLOGY_2.put(3, 2);

        TEST_1 = new BasicMudTesting(0.5f, 1.03f, 24, 10.1f, 8.9f, RHEOLOGY_1, GELS_2);
        TEST_2 = new BasicMudTesting(2f, 1.03f, 16, 15f, 7.2f, RHEOLOGY_2, GELS_1);
        NEW_TEST = new BasicMudTesting(1f, 1.12f, 32, 6f, 8f, RHEOLOGY_1, GELS_1);
        TEST_1.setSampleId(200000);
        TEST_2.setSampleId(200001);
        NEW_TEST.setId(200002);

        TESTS = new ArrayList<>();
        TESTS.add(TEST_1);
        TESTS.add(TEST_2);
    }
}
