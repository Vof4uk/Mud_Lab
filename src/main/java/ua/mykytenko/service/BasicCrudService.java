package ua.mykytenko.service;

/**
 * Created by Микитенко on 19.11.2016.
 */

import java.util.List;

public interface BasicCrudService<TO, E>{
    TO get(int id);
    List<TO> getAll();
    TO saveNew(E entity);
    void update(E entity);
    void delete(int id);
}
