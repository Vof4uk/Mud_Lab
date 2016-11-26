package ua.mykytenko.entities;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Микитенко on 19.10.2016.
 */
public abstract class BaseEntity {

    private String string;

    private boolean validString;

    private static final String ID = "id";

    private static final String ENTITY_NAME = "entity name";

    protected Map<String, String> entityMap = new HashMap<>();

    {
       setEntityName(this.getClass().getSimpleName());
    }

    public boolean isNew() {
        return getId() == null;
    }

    public Integer getId() {
        return integerGetter(ID);
    }

    public void setId(Integer id) {
        genericSetter(ID, id, Integer.class);
    }

    public String getEntityName(){
        return stringGetter(ENTITY_NAME);
    }

    public void setEntityName(String name){
        genericSetter(ENTITY_NAME, name, String.class);
    }

    public Map<String, String> getEntityMap() {
        return Collections.unmodifiableMap(entityMap);
    }

    protected  Integer integerGetter(String key){
        if(Objects.isNull(entityMap.get(key)))
            return null;
        return Integer.valueOf(entityMap.get(key));
    }

    protected  Float floatGetter(String key){
        if(Objects.isNull(entityMap.get(key)))
            return null;
        return Float.valueOf(entityMap.get(key));
    }

    protected  Boolean booleanGetter(String key){
        if(Objects.isNull(entityMap.get(key)))
            return null;
        return Boolean.valueOf(entityMap.get(key));
    }

    protected String stringGetter(String key){
        return entityMap.get(key);
    }

    protected LocalDate localDateGetter(String key){
        if(Objects.isNull(entityMap.get(key)))
            return null;
        return LocalDate.parse(entityMap.get(key));
    }

    protected <T> void genericSetter(String key, T value, Class<T> clazz){
        if(Objects.isNull(value)) entityMap.put(key, null);
        else
            entityMap.put(key, String.valueOf(value));
        validString = false;
    }

    @Override
    public String toString() {
        if(validString) return string;

        StringBuilder sb = getEntityMap().entrySet().stream()
                .collect(StringBuilder::new,
                        (s, e) -> s.append(String.format("\n %s = %s", e.getKey(), e.getValue())),
                        (x, y)->{});
        this.string = sb.toString();
        sb.setLength(0);
        validString = true;
        return this.string;
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return toString().equals(obj.toString());
    }
}
