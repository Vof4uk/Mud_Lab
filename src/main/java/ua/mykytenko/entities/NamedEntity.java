package ua.mykytenko.entities;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

@MappedSuperclass
public abstract class NamedEntity extends BaseEntity {

    protected static final String NAME = "name";

    public NamedEntity() {
        super();
    }

    public NamedEntity(String name) {
        setName(name);
    }

    @Transient
    public String getName() {
        return stringGetter(NAME);
    }

    public void setName(String name) {
        genericSetter(NAME, name, String.class);
    }
}
