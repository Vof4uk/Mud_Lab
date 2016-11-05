package ua.mykytenko.entities.samples;


import ua.mykytenko.entities.NamedEntity;

import java.time.LocalDate;
import java.util.Objects;

import static ua.mykytenko.entities.samples.Description.*;

/**
 * Created by Микитенко on 19.10.2016.
 */
public class Sample extends NamedEntity {

    private static final String FAMILY_ID = "Family ID";

    private static final String FAMILY_NAME = "Family name";

    private static final String WEIGHT = "Weight";

    private static final String ARRIVED = "Date of arrival";

    private static final String APPLICATIONS = "Applications";

    private static final String COMPOSITION = "Composition";

    private static final String VENDOR = "Vendor";

    private static final String MANUFACTURER = "Manufacturer";

    private static final String NOTES = "Additional notes";

    public Sample(){}

    public Sample(String testingFamily, String name, float weight, LocalDate arrived, Applications applications
            , Components components, Vendor vendor, Manufacturer manufacturer, String notes) {
        setName(name);
        setFamilyName(testingFamily);
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

    public String getFamilyName() {
        return stringGetter(FAMILY_NAME);
    }

    public void setFamilyName(String sampleFamily) {
        genericSetter(FAMILY_NAME, sampleFamily, String.class);
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

    public Applications getApplications() {
        if(Objects.isNull(entityMap.get(APPLICATIONS))) return null;
        return Applications.parse(entityMap.get(APPLICATIONS));
    }

    public void setApplications(Applications applications) {
        if(Objects.isNull(applications)) entityMap.put(APPLICATIONS, null);
        else
        entityMap.put(APPLICATIONS, applications.toString());
    }

    public Components getComposition() {
        if(Objects.isNull(entityMap.get(COMPOSITION))) return null;
        return Components.parse(entityMap.get(COMPOSITION));
    }

    public void setComposition(Components composition) {
        if(Objects.isNull(composition)) entityMap.put(COMPOSITION, null);
        else
        entityMap.put(COMPOSITION, composition.toString());
    }

    public Vendor getVendor() {
        if(Objects.isNull(entityMap.get(MANUFACTURER))) return null;
        return new Vendor(entityMap.get(VENDOR));
    }

    public void setVendor(Vendor vendor) {
        if(Objects.isNull(vendor)) entityMap.put(VENDOR, null);
        else
        entityMap.put(VENDOR, vendor.toString());
    }

    public Manufacturer getManufacturer() {
        if(Objects.isNull(entityMap.get(MANUFACTURER))) return null;
        return new Manufacturer(entityMap.get(MANUFACTURER));
    }

    public void setManufacturer(Manufacturer manufacturer) {
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
