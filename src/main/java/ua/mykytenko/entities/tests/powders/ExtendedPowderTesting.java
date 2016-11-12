package ua.mykytenko.entities.tests.powders;

import java.util.Map;

/**
 * Created by Микитенко on 19.10.2016.
 */
public class ExtendedPowderTesting extends BasicPowderTesting {
    private Map<Integer, Float> particleDistribution;
    private Float acidSolubility;

    public Map<Integer, Float> getParticleDistribution() {
        return particleDistribution;
    }

    public void setParticleDistribution(Map<Integer, Float> particleDistribution) {
        this.particleDistribution = particleDistribution;
    }

    public Float getAcidSolubility() {
        return acidSolubility;
    }

    public void setAcidSolubility(Float acidSolubility) {
        this.acidSolubility = acidSolubility;
    }
}
