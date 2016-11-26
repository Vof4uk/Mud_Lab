package ua.mykytenko;

import ua.mykytenko.entities.tests.powders.BasicPowderTesting;
import ua.mykytenko.to.TestingTo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Микитенко on 26.11.2016.
 */
public class BasicPowderTestingData {
    public static final BasicPowderTesting TEST_1;
    public static final BasicPowderTesting TEST_2;
    public static final BasicPowderTesting NEW_TEST;
    public static final List<BasicPowderTesting> TESTS = new ArrayList<>();

    public static final TestingTo TO_1;
    public static final TestingTo TO_2;
    public static final TestingTo NEW_TO;
    public static final List<TestingTo> TOS = new ArrayList<>();

    static {
        TEST_1 = new BasicPowderTesting(1500, 800, "some polymer", 5.6f, true);
        TEST_2 = new BasicPowderTesting(1800, 450, "some sort of ligno", 8f, true);
        NEW_TEST = new BasicPowderTesting(4200, 1600, "barite", 8f, false);
        TEST_1.setSampleId(1);
        TEST_2.setSampleId(2);
        TESTS.add(TEST_1);
        TESTS.add(TEST_2);

        TO_1 = new TestingTo(TEST_1);
        TO_2 = new TestingTo(TEST_2);
        NEW_TO = new TestingTo(NEW_TEST);
        TOS.add(TO_1);
        TOS.add(TO_2);
    }
}
