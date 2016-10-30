package ua.mykytenko.web.testing;

import ua.mykytenko.entities.tests.AbstractTesting;

import java.util.List;

/**
 * Created by Микитенко on 29.10.2016.
 */
public interface TestingController<T extends AbstractTesting> {
    public abstract T get(int id);

    public abstract void delete(int id);

    public abstract List<T> getAll();

    public abstract T save( T testing);
}
