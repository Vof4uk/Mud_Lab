package ua.mykytenko.entities.tests;

import ua.mykytenko.entities.BaseEntity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.util.Map;

@MappedSuperclass
public abstract class AbstractTesting extends BaseEntity {
    private static final String SAMPLE_ID = "Sample ID";

    @Column(name = "sample_id")
    public Integer getSampleId() {
        return integerGetter(SAMPLE_ID);
    }

    public void setSampleId(Integer sampleId) {
        genericSetter(SAMPLE_ID, sampleId, Integer.class);
    }

    @Transient
    public Map<String, String> getParametersMap() {
        return entityMap;
    }

}
