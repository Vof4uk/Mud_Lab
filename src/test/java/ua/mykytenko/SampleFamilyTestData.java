package ua.mykytenko;

import ua.mykytenko.entities.samples.SampleFamily;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static ua.mykytenko.entities.samples.Description.*;

public class SampleFamilyTestData {
    public static final SampleFamily FAMILY_1;
    public static final SampleFamily FAMILY_1M;
    public static final SampleFamily FAMILY_2;
    public static final SampleFamily FAMILY_3;

    public static final Company COMPANY_1;
    public static final Company COMPANY_2;
    public static final Company COMPANY_3;
    public static final Company COMPANY_4;

    public static final SampleFamily NEW_FAMILY;
    public static final List<SampleFamily> FAMILIES = new ArrayList<>();
    static{
        FAMILY_1 = new SampleFamily("polymers", 5000);
        FAMILY_1M = new SampleFamily("polymers", 5000);
        FAMILY_1M.setCurrentId(5001);
        FAMILY_1M.setId(10000000);
        FAMILY_2 = new SampleFamily("ligno", 8000);
        FAMILY_3 = new SampleFamily("other", 2000);
        FAMILY_1.setId(1);
        FAMILY_2.setId(2);
        FAMILY_3.setId(3);
        FAMILIES.addAll(Arrays.asList(FAMILY_1, FAMILY_2, FAMILY_3));
        NEW_FAMILY = new SampleFamily("alkalinity", 11000);

        FAMILY_1.setId(10000000);
        FAMILY_2.setId(10000001);
        FAMILY_3.setId(10000002);
        COMPANY_1 = new Company(10000003, "garage1");
        COMPANY_2 = new Company(10000004, "bazar1");
        COMPANY_3 = new Company(10000005, "garage2");
        COMPANY_4 = new Company(10000006, "olx.com");
    }
}
