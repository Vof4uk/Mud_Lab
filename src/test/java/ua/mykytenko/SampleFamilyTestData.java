package ua.mykytenko;

import ua.mykytenko.entities.samples.SampleFamily;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Микитенко on 13.11.2016.
 */
public class SampleFamilyTestData {
    public static final SampleFamily FAMILY_1;
    public static final SampleFamily FAMILY_2;
    public static final SampleFamily FAMILY_3;
    public static final SampleFamily NEW_FAMILY;
    public static final List<SampleFamily> FAMILIES = new ArrayList<>();
    static{
        FAMILY_1 = new SampleFamily("polymers", 5000);
        FAMILY_2 = new SampleFamily("ligno", 8000);
        FAMILY_3 = new SampleFamily("other", 2000);
        FAMILY_1.setId(1);
        FAMILY_2.setId(2);
        FAMILY_3.setId(3);
        FAMILIES.addAll(Arrays.asList(FAMILY_1, FAMILY_2, FAMILY_3));
        NEW_FAMILY = new SampleFamily("alkalinity", 11000);
    }
}
