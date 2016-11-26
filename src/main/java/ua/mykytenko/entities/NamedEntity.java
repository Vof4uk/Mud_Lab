package ua.mykytenko.entities;

/**
 * Created by Микитенко on 19.10.2016.
 */
public abstract class NamedEntity extends BaseEntity {

    private static final String NAME = "name";

    public NamedEntity() {
        super();
    }

    public NamedEntity(String name) {
        setName(name);
    }

    public String getName() {
        return stringGetter(NAME);
    }

    public void setName(String name) {
        genericSetter(NAME, name, String.class);
    }
}
