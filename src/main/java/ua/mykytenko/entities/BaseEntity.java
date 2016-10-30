package ua.mykytenko.entities;

/**
 * Created by Микитенко on 19.10.2016.
 */
public abstract class BaseEntity {
    protected Integer id;

    public boolean isNew()
    {
        return id == null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
