package ua.mykytenko.entities.samples;

import ua.mykytenko.entities.NamedEntity;

/**
 * Created by Микитенко on 23.10.2016.
 */
public class SampleFamily extends NamedEntity{
    private final int initialId;

    public SampleFamily(String name, int initialId) {
        super(name);
        this.initialId = initialId;
    }

    public int getInitialId() {
        return initialId;
    }

    @Override
    public String toString() {
        return getName();
    }
}
