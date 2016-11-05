package ua.mykytenko.to;

import ua.mykytenko.entities.tests.AbstractTesting;

import java.util.*;

/**
 * Created by Микитенко on 04.11.2016.
 */
public class TestingTo {
    private Map<String, String> core;
    private static Map<String, String> translation;
    private List<String> order;

    public TestingTo(AbstractTesting testing){
        core = Collections.unmodifiableMap(testing.getParametersMap());
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

    public static void setTranslation(Map<String, String> translation1){
        translation = translation1;
    }

    public Map<String, String> getParametersMapTranslated(){
        return order.stream()
                .collect(LinkedHashMap::new
                        , (map, name) ->
                                map.put(translation.get(name) == null? name : translation.get(name) , core.get(name))
                        , (m, n) -> {});
    }

    public Map<String, String> getParametersMap(){
        return order.stream()
                .collect(LinkedHashMap::new,
                        (map, name) -> map.put(name, core.get(name)),
                                (m, n) -> {});
    }

}
