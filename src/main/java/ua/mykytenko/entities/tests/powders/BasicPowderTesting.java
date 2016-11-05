package ua.mykytenko.entities.tests.powders;

import ua.mykytenko.entities.tests.AbstractTesting;

import java.util.Map;

/**
 * Created by Микитенко on 19.10.2016.
 */
public class BasicPowderTesting extends AbstractTesting {

    private static final String SPECIFIC_GRAVITY = "Specific gravity";
    private static final String BULK_SPECIFIC_GRAVITY = "Bulk specific gravity";
    private static final String DESCRIPTION = "Appearence";
    private static final String WETNESS = "Wetness";
    private static final String IS_WATER_SOLUBLE = "Water soluble";

    public BasicPowderTesting() {
    }

    public BasicPowderTesting(Integer sg, Integer packingSg, String appearance, Float wetness, Boolean waterSoluble) {
        setSg(sg);
        setBulkSg(packingSg);
        setAppearance(appearance);
        setWetness(wetness);
        setWaterSoluble(waterSoluble);
    }

    public Integer getSg() {
        return integerGetter(SPECIFIC_GRAVITY);
    }

    public void setSg(Integer sg) {
        genericSetter(SPECIFIC_GRAVITY, sg, Integer.class);
    }

    public Integer getBulkSg() {
        return integerGetter(BULK_SPECIFIC_GRAVITY);
    }

    public void setBulkSg(Integer packingSg) {
        genericSetter(BULK_SPECIFIC_GRAVITY, packingSg, Integer.class);
    }

    public String getAppearance() {
        return stringGetter(DESCRIPTION);
    }

    public void setAppearance(String appearance) {
        genericSetter(DESCRIPTION, appearance, String.class);
    }

    public Float getWetness() {
        return floatGetter(WETNESS);
    }

    public void setWetness(Float wetness) {
        genericSetter(WETNESS, wetness, Float.class);
    }

    public Boolean getWaterSoluble() {
        return booleanGetter(IS_WATER_SOLUBLE);
    }

    public void setWaterSoluble(Boolean waterSoluble) {
        genericSetter(IS_WATER_SOLUBLE, waterSoluble, Boolean.class);
    }

    @Override
    public Map<String, String> getParametersMap() {
       return super.getParametersMap();
    }
}
