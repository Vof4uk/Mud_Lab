package ua.mykytenko.entities.samples;


import ua.mykytenko.entities.NamedEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import static ua.mykytenko.entities.samples.Description.*;

@Entity
@Table(name = "samples")
public class Sample extends NamedEntity {

    private static final String FAMILY_ID = "Family ID";

    private static final String FAMILY = "Family";

    private static final String WEIGHT = "Weight";

    private static final String ARRIVED = "Date of arrival";

    private static final String APPLICATIONS = "Applications";

    private static final String COMPOSITION = "Composition";

    private static final String VENDOR = "Vendor";

    private static final String MANUFACTURER = "Manufacturer";

    private static final String NOTES = "Additional notes";

    public Sample(){}

    public Sample(SampleFamily testingFamily, String name, float weight, LocalDate arrived, Applications applications
            , Components components, Company vendor, Company manufacturer, String notes) {
        setName(name);
        setFamily(testingFamily);
        setWeight(weight);
        setArrived(arrived);
        setApplications(applications);
        setVendor(vendor);
        setManufacturer(manufacturer);
        setComposition(components);
        setNotes(notes);
    }

    public Integer getFamilyId() {
        return integerGetter(FAMILY_ID);
    }

    public void setFamilyId(Integer familyId) {
        genericSetter(FAMILY_ID, familyId, Integer.class);
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "family_id")
    public SampleFamily getFamily() {
        return SampleFamily.parse(stringGetter(FAMILY));
    }

    public void setFamily(SampleFamily sampleFamily) {
        genericSetter(FAMILY, sampleFamily, SampleFamily.class);
    }

    public Float getWeight() {
        return floatGetter(WEIGHT);
    }

    public void setWeight(Float weight) {
        genericSetter(WEIGHT, weight, Float.class);
    }

    public LocalDate getArrived() {
        return localDateGetter(ARRIVED);
    }

    public void setArrived(LocalDate arrived) {
        genericSetter(ARRIVED, arrived, LocalDate.class);
    }


    @ElementCollection
    @JoinTable(name = "sample_applications", joinColumns = @JoinColumn(name = "sample_id"))
    @Column(name = "name")
    public Set<String> getApplications() {
        if(Objects.isNull(entityMap.get(APPLICATIONS))) return null;
        return Applications.parse(entityMap.get(APPLICATIONS));
    }

    public void setApplications(Set<String> applications) {
        if(Objects.isNull(applications)) entityMap.put(APPLICATIONS, null);
        else
        entityMap.put(APPLICATIONS, applications.toString());
    }

    @ElementCollection
    public Map<String, Integer> getComposition() {
        if(Objects.isNull(entityMap.get(COMPOSITION))) return null;
        return Components.parse(entityMap.get(COMPOSITION));
    }

    public void setComposition(Map<String, Integer> composition) {
        if(Objects.isNull(composition)) entityMap.put(COMPOSITION, null);
        else
        entityMap.put(COMPOSITION, composition.toString());
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vendor_id")
    public Company getVendor() {
        if(Objects.isNull(entityMap.get(VENDOR))) return null;
        return new Company(entityMap.get(VENDOR));
    }

    public void setVendor(Company vendor) {
        if(Objects.isNull(vendor)) entityMap.put(VENDOR, null);
        else
        entityMap.put(VENDOR, vendor.toString());
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "manufacturer_id")
    public Company getManufacturer() {
        if(Objects.isNull(entityMap.get(MANUFACTURER))) return null;
        return new Company(entityMap.get(MANUFACTURER));
    }

    public void setManufacturer(Company manufacturer) {
        if(Objects.isNull(manufacturer)) entityMap.put(MANUFACTURER, null);
        else
        entityMap.put(MANUFACTURER, manufacturer.toString());
    }

    public String getNotes() {
        return stringGetter(NOTES);
    }

    public void setNotes(String notes) {
        genericSetter(NOTES, notes, String.class);
    }
}
