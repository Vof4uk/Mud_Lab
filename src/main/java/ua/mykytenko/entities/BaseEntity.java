package ua.mykytenko.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@MappedSuperclass
@Access(AccessType.PROPERTY)
public abstract class BaseEntity {

    private String string;

    private boolean validString;

    protected static final String ID = "id";

    private static final String ENTITY_NAME = "entity name";

    protected Map<String, String> entityMap = new HashMap<>();

    {
       setEntityName(this.getClass().getSimpleName());
    }

    @Transient
    public boolean isNew() {
        return getId() == null;
    }

    @Id
    public Integer getId() {
        return integerGetter(ID);
    }

    public void setId(Integer id) {
        genericSetter(ID, id, Integer.class);
    }

    @Transient
    public String getEntityName(){
        return stringGetter(ENTITY_NAME);
    }

    public void setEntityName(String name){
        genericSetter(ENTITY_NAME, name, String.class);
    }

    @Transient
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

    protected static Map<String, String> parseLine(String line){
        String[] lines = line.split("[\n,;.]");
        return Arrays.stream(lines)
                .collect(HashMap::new, (m, l) ->{
                    String[] entry = l.split("=");
                    String key = null, val = null;
                    if(entry.length >= 1) {
                        key = entry[0].trim();
                        if (entry.length >= 2)
                            val = entry[1].trim();
                        m.put(key, val);
                    }
                }, (a,b)->{});
    }

    @Override
    public int hashCode() {
        return this.entityMap.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof BaseEntity)) return false;
        Set<String> keys = new HashSet<>();
                keys.addAll(this.getEntityMap().keySet());
        final Map<String, String> otherMap = ((BaseEntity) obj).getEntityMap();
        keys.addAll(otherMap.keySet());

        return !keys.stream()
                .map(key -> {
                    if (this.entityMap.get(key) == null)
                        return null == (otherMap.get(key));
                    return this.entityMap.get(key).equals(otherMap.get(key));
                })
                .filter(b -> !b)
                .findFirst().isPresent();
    }
}
