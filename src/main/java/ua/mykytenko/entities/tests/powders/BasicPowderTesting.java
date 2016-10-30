package ua.mykytenko.entities.tests.powders;

import ua.mykytenko.entities.tests.AbstractTesting;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Микитенко on 19.10.2016.
 */
public class BasicPowderTesting extends AbstractTesting {
    protected Integer sg;
    protected Integer packingSg;
    protected String appearance;
    protected Float wetness;
    protected Boolean waterSoluble;

    public BasicPowderTesting() {
    }

    public BasicPowderTesting(Integer sg, Integer packingSg, String appearance, Float wetness, Boolean waterSoluble) {
        this.sg = sg;
        this.packingSg = packingSg;
        this.appearance = appearance;
        this.wetness = wetness;
        this.waterSoluble = waterSoluble;
    }

    public Integer getSg() {
        return sg;
    }

    public void setSg(Integer sg) {
        this.sg = sg;
    }

    public Integer getPackingSg() {
        return packingSg;
    }

    public void setPackingSg(Integer packingSg) {
        this.packingSg = packingSg;
    }

    public String getAppearance() {
        return appearance;
    }

    public void setAppearance(String appearance) {
        this.appearance = appearance;
    }

    public Float getWetness() {
        return wetness;
    }

    public void setWetness(Float wetness) {
        this.wetness = wetness;
    }

    public Boolean getWaterSoluble() {
        return waterSoluble;
    }

    public void setWaterSoluble(Boolean waterSoluble) {
        this.waterSoluble = waterSoluble;
    }

    @Override
    public Map<String, String> getParametersMap() {
        Map<String, String> resultmap = super.getParametersMap();
        resultmap.putAll(getParametersMap(this));
        return resultmap;
    }
}
