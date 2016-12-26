package ua.mykytenko.entities.samples;

import ua.mykytenko.entities.NamedEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.stream;

@Entity
@Table(name = "sample_families")
public class SampleFamily extends NamedEntity{

    private static final String INITIAL_ID = "initial id";

    private static final String CURRENT_ID = "current id";

    private List<Sample> allSamples;

    public SampleFamily() {
    }

    public SampleFamily(String name, int initialId) {
        super(name);
        setInitialId(initialId);
        setCurrentId(initialId);
    }

    public void setInitialId(Integer initialId){
        genericSetter(INITIAL_ID, initialId, Integer.class);
    }

    @Column(name ="initial_family_id")
    public Integer getInitialId() {
        return integerGetter(INITIAL_ID);
    }

    public void setCurrentId(Integer currentId) {
        genericSetter(CURRENT_ID, currentId, Integer.class);
    }

    @Column(name = "current_family_id")
    public Integer getCurrentId() {
        return integerGetter(CURRENT_ID);
    }

    @OneToMany(mappedBy = "family")
    public List<Sample> getAllSamples() {
        return allSamples;
    }

    public void setAllSamples(List<Sample> allSamples) {
        this.allSamples = allSamples;
    }

    public static SampleFamily parse(String s){
        SampleFamily sf = new SampleFamily();
        Map<String, String > map = parseLine(s);
        if(map.get(ID) != null) sf.setId(Integer.valueOf(map.get(ID)));
        if(map.get(NAME) != null) sf.setName(map.get(NAME));
        if(map.get(INITIAL_ID) != null) sf.setInitialId(Integer.valueOf(map.get(INITIAL_ID)));
        if(map.get(CURRENT_ID) != null) sf.setCurrentId(Integer.valueOf(map.get(CURRENT_ID)));

        return sf;
    }
}
