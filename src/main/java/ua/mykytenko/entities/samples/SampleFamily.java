package ua.mykytenko.entities.samples;

import ua.mykytenko.entities.NamedEntity;

/**
 * Created by Микитенко on 23.10.2016.
 */
public class SampleFamily extends NamedEntity{
    private static final String INITIAL_ID = "initial id";

    public SampleFamily(String name, int initialId) {
        super(name);
        setInitialId(initialId);
    }

    private void setInitialId(Integer initialId){
        genericSetter(INITIAL_ID, initialId, Integer.class);
    }

    public Integer getInitialId() {
        return integerGetter(INITIAL_ID);
    }
}
