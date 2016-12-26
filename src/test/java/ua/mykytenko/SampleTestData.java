package ua.mykytenko;

import ua.mykytenko.entities.samples.Sample;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static ua.mykytenko.SampleFamilyTestData.*;
import static ua.mykytenko.entities.samples.Description.*;

public class SampleTestData {
    public static final Sample SAMPLE_1;
    public static final Sample SAMPLE_2;
    public static final Sample NEW_SAMPLE;
    public static final Sample COMPARE_SAMPLE;
    public static final List<Sample> SAMPLES = new ArrayList<>();

    static {
        Applications application1 = Applications.parse("viscosifier");
        Applications application2 = Applications.parse("thinner; defloc");
        Components composition1 = Components.parse("100% - xanthan gum");
        Components composition2 = Components.parse("90% - potassium humate;" + "\n" +
                "10% - caustic potassium" );
        SAMPLE_1 = new Sample(FAMILY_1, "barazan D", 0.9f, LocalDate.now(),application1, composition1,
                COMPANY_2, COMPANY_1, "some notes");
        SAMPLE_2 = new Sample(FAMILY_2, "УЩР", 1.1f, LocalDate.now(),application2, composition2,
                COMPANY_4, COMPANY_3, "other notes");

        NEW_SAMPLE = new Sample(FAMILY_1, "cmc", 1.1f, LocalDate.now(),application2, composition2,
                COMPANY_4, COMPANY_3, "other notes");

        COMPARE_SAMPLE = new Sample(FAMILY_1M, "cmc", 1.1f, LocalDate.now(),application2, composition2,
                COMPANY_4, COMPANY_3, "other notes");
        COMPARE_SAMPLE.setFamilyId(5001);


        SAMPLES.add(SAMPLE_1);
        SAMPLES.add(SAMPLE_2);
        SAMPLES.get(0).setId(1);
        SAMPLES.get(1).setId(2);
        SAMPLE_1.setFamilyId(5000);
        NEW_SAMPLE.setFamilyId(5001);
        SAMPLE_2.setFamilyId(8000);



    }

}
