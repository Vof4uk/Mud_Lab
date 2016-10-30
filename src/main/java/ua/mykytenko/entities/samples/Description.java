package ua.mykytenko.entities.samples;

import ua.mykytenko.entities.NamedEntity;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Микитенко on 19.10.2016.
 */
public class Description {

    private Description(){}

    public static class Vendor extends NamedEntity {
        public Vendor(String name) {
            super(name);
        }

        @Override
        public String toString() {
            return getName();
        }
    }

    public static class ProductType extends NamedEntity{
        public ProductType(String name) {
            super(name);
        }

        @Override
        public String toString() {
            return getName();
        }
    }

    public static class ProductComponent extends NamedEntity{
        public ProductComponent(String name) {
            super(name);
        }

        @Override
        public String toString() {
            return getName();
        }
    }

    public static class Manufacturer extends NamedEntity{
        public Manufacturer(String name) {
            super(name);
        }

        @Override
        public String toString() {
            return getName();
        }
    }

    public static class Applications implements Set<String>{
        private Set<String> set = new HashSet<>();

        private Applications(){}

        public static Applications parse(String appl){
            Applications applications = new Applications();
            String[] parsedAppl = appl.split("[ ,;.]+");
            applications.addAll(Arrays.asList(parsedAppl));
            return applications;
        }

        @Override
        public String toString() {
            String[] array = set.stream().toArray(String[]::new);
                    return Arrays.toString(array).replaceAll("[\\[\\]]", "");
        }

        @Override
        public int size() {
            return set.size();
        }

        @Override
        public boolean isEmpty() {
            return set.isEmpty();
        }

        @Override
        public boolean contains(Object o) {
            return set.contains(o);
        }

        @Override
        public Iterator<String> iterator() {
            return set.iterator();
        }

        @Override
        public Object[] toArray() {
            return set.toArray();
        }

        @Override
        public <T> T[] toArray(T[] a) {
            return set.toArray(a);
        }

        @Override
        public boolean add(String s) {
            return set.add(s);
        }

        @Override
        public boolean remove(Object o) {
            return set.remove(o);
        }

        @Override
        public boolean containsAll(Collection<?> c) {
            return set.containsAll(c);
        }

        @Override
        public boolean addAll(Collection<? extends String> c) {
            return set.addAll(c);
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            return set.retainAll(c);
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            return set.removeAll(c);
        }

        @Override
        public void clear() {
            set.clear();
        }

        @Override
        public boolean equals(Object o) {
            return set.equals(o);
        }

        @Override
        public int hashCode() {
            return set.hashCode();
        }

        @Override
        public Spliterator<String> spliterator() {
            return set.spliterator();
        }

        @Override
        public boolean removeIf(Predicate<? super String> filter) {
            return set.removeIf(filter);
        }

        @Override
        public Stream<String> stream() {
            return set.stream();
        }

        @Override
        public Stream<String> parallelStream() {
            return set.parallelStream();
        }

        @Override
        public void forEach(Consumer<? super String> action) {
            set.forEach(action);
        }
    }

    public static class Components implements Map<String, Integer>{
        private Map<String, Integer> map = new HashMap<>();

        private Components() {
        }

        public static Components parse(String comps){
            Components c = Arrays.stream(comps.split("[,.;\n]+"))
                    .filter(line -> line.trim().matches("[0-9]{1,3}% -? ?[\\w\\W]+"))
                    .collect(Components::new,
                            (m, line) -> m.put(line.replaceAll("[\\w\\W]*%[ -]*", "").trim(),
                                               Integer.valueOf(line.replaceAll("%[\\w\\W]*", "").trim())),
                            (m, u) -> {});

            return c;
        }

        @Override
        public String toString() {

            return map.entrySet().stream()
                    .map(entry ->new StringBuilder(entry.getValue() + "% - " + entry.getKey()+"; \n"))
                    .reduce(new StringBuilder(), StringBuilder::append).toString();
        }

        @Override
        public int size() {
            return map.size();
        }

        @Override
        public boolean isEmpty() {
            return map.isEmpty();
        }

        @Override
        public boolean containsKey(Object key) {
            return map.containsKey(key);
        }

        @Override
        public boolean containsValue(Object value) {
            return map.containsValue(value);
        }

        @Override
        public Integer get(Object key) {
            return map.get(key);
        }

        @Override
        public Integer put(String key, Integer value) {
            return map.put(key, value);
        }

        @Override
        public Integer remove(Object key) {
            return map.remove(key);
        }

        @Override
        public void putAll(Map<? extends String, ? extends Integer> m) {
            map.putAll(m);
        }

        @Override
        public void clear() {
            map.clear();
        }

        @Override
        public Set<String> keySet() {
            return map.keySet();
        }

        @Override
        public Collection<Integer> values() {
            return map.values();
        }

        @Override
        public Set<Entry<String, Integer>> entrySet() {
            return map.entrySet();
        }

        @Override
        public boolean equals(Object o) {
            return map.equals(o);
        }

        @Override
        public int hashCode() {
            return map.hashCode();
        }

        @Override
        public Integer getOrDefault(Object key, Integer defaultValue) {
            return map.getOrDefault(key, defaultValue);
        }

        @Override
        public void forEach(BiConsumer<? super String, ? super Integer> action) {
            map.forEach(action);
        }

        @Override
        public void replaceAll(BiFunction<? super String, ? super Integer, ? extends Integer> function) {
            map.replaceAll(function);
        }

        @Override
        public Integer putIfAbsent(String key, Integer value) {
            return map.putIfAbsent(key, value);
        }

        @Override
        public boolean remove(Object key, Object value) {
            return map.remove(key, value);
        }

        @Override
        public boolean replace(String key, Integer oldValue, Integer newValue) {
            return map.replace(key, oldValue, newValue);
        }

        @Override
        public Integer replace(String key, Integer value) {
            return map.replace(key, value);
        }

        @Override
        public Integer computeIfAbsent(String key, Function<? super String, ? extends Integer> mappingFunction) {
            return map.computeIfAbsent(key, mappingFunction);
        }

        @Override
        public Integer computeIfPresent(String key, BiFunction<? super String, ? super Integer, ? extends Integer> remappingFunction) {
            return map.computeIfPresent(key, remappingFunction);
        }

        @Override
        public Integer compute(String key, BiFunction<? super String, ? super Integer, ? extends Integer> remappingFunction) {
            return map.compute(key, remappingFunction);
        }

        @Override
        public Integer merge(String key, Integer value, BiFunction<? super Integer, ? super Integer, ? extends Integer> remappingFunction) {
            return map.merge(key, value, remappingFunction);
        }
    }
}
