package ua.mykytenko.entities.tests;

import ua.mykytenko.entities.BaseEntity;

import java.util.Map;

/**
 * Created by Микитенко on 19.10.2016.
 */
public abstract class AbstractTesting extends BaseEntity {
    private static final String SAMPLE_ID = "Sample ID";

    public Integer getSampleId() {
        return integerGetter(SAMPLE_ID);
    }

    public void setSampleId(Integer sampleId) {
        genericSetter(SAMPLE_ID, sampleId, Integer.class);
    }

    public Map<String, String> getParametersMap() {
        return entityMap;
    }


}
