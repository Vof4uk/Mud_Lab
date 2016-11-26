package ua.mykytenko;

import ua.mykytenko.entities.samples.Description;
import ua.mykytenko.entities.samples.Sample;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Микитенко on 13.11.2016.
 */
public class SampleTestData {
    public static final Sample SAMPLE_1;
    public static final Sample SAMPLE_2;
    public static final Sample NEW_SAMPLE;
    public static final List<Sample> SAMPLES = new ArrayList<>();

    static {
        Description.Applications application1 = Description.Applications.parse("viscosifier");
        Description.Applications application2 = Description.Applications.parse("thinner; defloc");
        Description.Components composition1 = Description.Components.parse("100% - xanthan gum");
        Description.Components composition2 = Description.Components.parse("90% - xanthan gum;" + "\n" +
                "10% - caustic pot." );
        SAMPLE_1 = new Sample("polymers", "barazan D", 0.9f, LocalDate.now(),application1, composition1,
                new Description.Vendor("garage1"), new Description.Manufacturer("bazar1"), "some notes");
        SAMPLE_2 = new Sample("ligno", "УЩР", 1.1f, LocalDate.now(),application2, composition2,
                new Description.Vendor("garage2"), new Description.Manufacturer("olx.com"), "other notes");
        NEW_SAMPLE = new Sample("polymers", "cmc", 1.1f, LocalDate.now(),application2, composition2,
                new Description.Vendor("garage2"), new Description.Manufacturer("olx.com"), "other notes");

        SAMPLES.add(SAMPLE_1);
        SAMPLES.add(SAMPLE_2);
        SAMPLES.get(0).setId(1);
        SAMPLES.get(1).setId(2);
        SAMPLE_1.setFamilyId(5000);
        NEW_SAMPLE.setFamilyId(5000);
        SAMPLE_2.setFamilyId(8000);



    }

}
