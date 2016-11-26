package ua.mykytenko.to;

import ua.mykytenko.entities.tests.AbstractTesting;

import java.util.*;

/**
 * Created by Микитенко on 04.11.2016.
 */
public class TestingTo {
    private static Map<String, String> translation = new HashMap<>();
    private Map<String, String> core;
    private List<String> order;

    private String entityName;

    public TestingTo() {
    }

    public TestingTo(AbstractTesting testing) {
        core = Collections.unmodifiableMap(testing.getParametersMap());
        entityName = testing.getEntityName();
    }

    public static void setTranslation(Map<String, String> translation1) {
        translation = translation1;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public Map<String, String> getCore() {
        return core;
    }

    public void setCore(Map<String, String> core) {
        this.core = core;
    }

    public List<String> getOrder() {
        return order;
    }

    public void setOrder(List<String> order) {
        this.order = order;
    }

    public Map<String, String> getParametersMapTranslated() {
        if (order == null || core == null) return Collections.emptyMap();
        return order.stream()
                .collect(LinkedHashMap::new
                        , (map, name) ->
                                map.put(translation.get(name) == null ? name : translation.get(name), core.get(name))
                        , (m, n) -> {
                        });
    }

    public Map<String, String> getParametersMap() {
        if (order == null || core == null) return Collections.emptyMap();
        return order.stream()
                .collect(LinkedHashMap::new,
                        (map, name) -> map.put(name, core.get(name)),
                        (m, n) -> {
                        });
    }

    public Set<String> keySet(){
        return getParametersMapTranslated().keySet();
    }

    public Collection<String> values(){
        return getParametersMap().values();
    }

    @Override
    public boolean equals(Object obj) {
        if(! (obj instanceof TestingTo)) return false;
        return this.core.equals(((TestingTo)obj).getCore());
    }
}
