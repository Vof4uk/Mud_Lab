package ua.mykytenko.entities.samples;


import ua.mykytenko.entities.NamedEntity;

import java.time.LocalDate;

import static ua.mykytenko.entities.samples.Description.*;

/**
 * Created by Микитенко on 19.10.2016.
 */
public class Sample extends NamedEntity {

    private Integer familyId;

    private String familyName;

    private float weight;

    private LocalDate arrived;

    private Applications applications;

    private Components composition;

    private Vendor vendor;

    private Manufacturer manufacturer;

    private String notes;

    public Sample(){}

    public Sample(String testingFamily, String name, float weight, LocalDate arrived, Applications applications
            , Components composition, Vendor vendor, Manufacturer manufacturer, String notes) {
        this.setName(name);
        this.familyName = testingFamily;
        this.weight = weight;
        this.arrived = arrived;
        this.applications = applications;
        this.composition = composition;
        this.vendor = vendor;
        this.manufacturer = manufacturer;
        this.notes = notes;
    }

    public Integer getFamilyId() {
        return familyId;
    }

    public void setFamilyId(Integer familyId) {
        this.familyId = familyId;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String testingFamily) {
        this.familyName = testingFamily;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public LocalDate getArrived() {
        return arrived;
    }

    public void setArrived(LocalDate arrived) {
        this.arrived = arrived;
    }

    public Applications getApplications() {
        return applications;
    }

    public void setApplications(Applications applications) {
        this.applications = applications;
    }

    public Components getComposition() {
        return composition;
    }

    public void setComposition(Components composition) {
        this.composition = composition;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
