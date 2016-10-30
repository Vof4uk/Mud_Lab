package ua.mykytenko.entities.tests;

import ua.mykytenko.entities.BaseEntity;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Микитенко on 19.10.2016.
 */
public abstract class AbstractTesting extends BaseEntity {
    protected Integer sampleId;

    public Integer getSampleId() {
        return sampleId;
    }

    public void setSampleId(Integer sampleId) {
        this.sampleId = sampleId;
    }

    public Map<String, String> getParametersMap() {
        return getParametersMap(this);
    }

    protected Map<String, String> getParametersMap(final AbstractTesting testing){
        Method[] methods = testing.getClass().getDeclaredMethods();

        return Arrays.stream(methods)
                .filter(m -> m.getName().startsWith("get"))
                .collect(HashMap<String, String>::new,
                        (map, method) -> {
                            try {
                                map.put(method.getName(), method.invoke(testing).toString());
                            } catch (IllegalAccessException | InvocationTargetException e) {
                                throw new RuntimeException(e.getMessage());
                            }
                        },
                        (m, u) -> {});
    }
}
