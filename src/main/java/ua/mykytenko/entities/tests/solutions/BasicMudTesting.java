package ua.mykytenko.entities.tests.solutions;

import ua.mykytenko.entities.tests.AbstractTesting;

import java.util.Map;

/**
 * Created by Микитенко on 19.10.2016.
 */
public class BasicMudTesting extends AbstractTesting {

    private Float sampleContent;

    private Float mudWeight;

    private Integer viscosity;

    private Float fluidLoss;

    private Float pH;

    private Map<Integer, Integer> rheology;

    private Map<Integer, Integer> gels;

    public BasicMudTesting() {
    }

    public BasicMudTesting(Float sampleContent, Float mudWeight, Integer viscosity, Float fluidLoss,
                           Float pH, Map<Integer, Integer> rheology, Map<Integer, Integer> gels) {
        this.sampleContent = sampleContent;
        this.mudWeight = mudWeight;
        this.viscosity = viscosity;
        this.fluidLoss = fluidLoss;
        this.pH = pH;
        this.rheology = rheology;
        this.gels = gels;
    }

    public Float getSampleContent() {
        return sampleContent;
    }

    public void setSampleContent(Float sampleContent) {
        this.sampleContent = sampleContent;
    }

    public Float getMudWeight() {
        return mudWeight;
    }

    public void setMudWeight(Float mudWeight) {
        this.mudWeight = mudWeight;
    }

    public Integer getViscosity() {
        return viscosity;
    }

    public void setViscosity(Integer viscosity) {
        this.viscosity = viscosity;
    }

    public Float getFluidLoss() {
        return fluidLoss;
    }

    public void setFluidLoss(Float fluidLoss) {
        this.fluidLoss = fluidLoss;
    }

    public Float getpH() {
        return pH;
    }

    public void setpH(Float pH) {
        this.pH = pH;
    }

    public Map<Integer, Integer> getRheology() {
        return rheology;
    }

    public void setRheology(Map<Integer, Integer> rheology) {
        this.rheology = rheology;
    }

    public Map<Integer, Integer> getGels() {
        return gels;
    }

    public void setGels(Map<Integer, Integer> gels) {
        this.gels = gels;
    }
}
