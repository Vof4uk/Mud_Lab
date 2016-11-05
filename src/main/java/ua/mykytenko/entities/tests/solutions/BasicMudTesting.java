package ua.mykytenko.entities.tests.solutions;

import ua.mykytenko.entities.tests.AbstractTesting;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Микитенко on 19.10.2016.
 */
public class BasicMudTesting extends AbstractTesting {

    private static final String SAMPLE_CONTENT = "Contents of sample";

    private static final String MUD_WEIGHT = "Mud weight";

    private static final String VISCOSITY = "Viscosity";

    private static final String FLUID_LOSS = "Fluid loss";

    private static final String PH = "PH";

    private static final String RHEOLOGY = "rheometr readings @%s%s";

    private static final Integer[] RHEOLOGY_SPEED = {3, 6 , 100 , 200, 300, 600};

    private static final String GELS = "GEL after %s%s";

    private static final Integer[] GELS_SECONDS = {10, 60, 600};

    public BasicMudTesting() {
    }

    public BasicMudTesting(Float sampleContent, Float mudWeight, Integer viscosity, Float fluidLoss,
                           Float pH, Map<Integer, Integer> rheology, Map<Integer, Integer> gels) {
        setSampleContent(sampleContent);
        setMudWeight(mudWeight);
        setViscosity(viscosity);
        setFluidLoss(fluidLoss);
        setpH(pH);
        setRheology(rheology);
        setGels(gels);
    }

    public Float getSampleContent() {
        return floatGetter(SAMPLE_CONTENT);
    }

    public void setSampleContent(Float sampleContent) {
        genericSetter(SAMPLE_CONTENT, sampleContent, Float.class);
    }

    public Float getMudWeight() {
        return floatGetter(MUD_WEIGHT);
    }

    public void setMudWeight(Float mudWeight) {
        genericSetter(MUD_WEIGHT, mudWeight, Float.class);
    }

    public Integer getViscosity() {
        return integerGetter(VISCOSITY);
    }

    public void setViscosity(Integer viscosity) {
        genericSetter(VISCOSITY, viscosity, Integer.class);
    }

    public Float getFluidLoss() {
        return floatGetter(FLUID_LOSS);
    }

    public void setFluidLoss(Float fluidLoss) {
        genericSetter(FLUID_LOSS, fluidLoss, Float.class);
    }

    public Float getpH() {
        return floatGetter(PH);
    }

    public void setpH(Float pH) {
        genericSetter(PH, pH, Float.class);
    }

    public Map<Integer, Integer> getRheology() {
        Map<Integer, Integer> resultMap = new HashMap<>();
        for (Integer s: RHEOLOGY_SPEED) {
            String key = String.format(RHEOLOGY, s, "rpm");
            if(Objects.nonNull( entityMap.get(key)))
            {
                resultMap.put(s, Integer.valueOf(entityMap.get(key)));
            }
        }
        return resultMap;
    }

    public void setRheology(Map<Integer, Integer> rheology) {
        for (Integer s: RHEOLOGY_SPEED) {
            if(Objects.nonNull( rheology.get(s))){
                entityMap.put(String.format(RHEOLOGY, s, "rpm"), String.valueOf(rheology.get(s)));
            }
        }
    }

    public Map<Integer, Integer> getGels() {
        Map<Integer, Integer> resultMap = new HashMap<>();
        for (Integer s: GELS_SECONDS) {
            String key = String.format(GELS, s, "s.");
            if(Objects.nonNull( entityMap.get(key)))
            {
                resultMap.put(s, Integer.valueOf(entityMap.get(key)));
            }
        }
        return resultMap;
    }

    public void setGels(Map<Integer, Integer> gels) {
        for (Integer s: GELS_SECONDS) {
            if(Objects.nonNull( gels.get(s))){
                entityMap.put(String.format(GELS, s, "s."), String.valueOf(gels.get(s)));
            }
        }
    }
}
