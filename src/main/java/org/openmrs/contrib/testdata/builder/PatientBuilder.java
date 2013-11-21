package org.openmrs.contrib.testdata.builder;

import org.openmrs.Concept;
import org.openmrs.Location;
import org.openmrs.Patient;
import org.openmrs.PatientIdentifier;
import org.openmrs.PatientIdentifierType;
import org.openmrs.PersonAddress;
import org.openmrs.PersonAttribute;
import org.openmrs.PersonName;
import org.openmrs.User;
import org.openmrs.contrib.testdata.TestDataManager;

import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 *
 */
public class PatientBuilder extends TestDataBuilder<Patient> {

    private Patient entity = new Patient();

    public PatientBuilder(TestDataManager testDataManager) {
        super(testDataManager);
    }

    @Override
    public Patient save() {
        Patient created = testDataManager.getPatientService().savePatient(entity);
        testDataManager.created(Patient.class, created);
        return created;
    }

    public PatientBuilder gender(String gender) {
        entity.setGender(gender);
        return this;
    }

    public PatientBuilder birthdate(Date birthdate) {
        entity.setBirthdate(birthdate);
        return this;
    }

    public PatientBuilder birthdate(String birthdate) {
        entity.setBirthdate(parseDate(birthdate));
        return this;
    }

    public PatientBuilder birthdateEstimated(boolean estimated) {
        entity.setBirthdateEstimated(estimated);
        return this;
    }

    /**
     * @param birthdate may be a java.util.Date or a String
     * @param estimated
     * @return
     * @see #parseDate(String)
     */
    public PatientBuilder birthdate(Object birthdate, boolean estimated) {
        if (birthdate instanceof Date) {
            entity.setBirthdate((Date) birthdate);
        }
        else {
            entity.setBirthdate(parseDate((String) birthdate));
        }
        entity.setBirthdateEstimated(estimated);
        return this;
    }

    public PatientBuilder dead(boolean dead) {
        entity.setDead(dead);
        return this;
    }

    public PatientBuilder deathDate(Date deathDate) {
        entity.setDeathDate(deathDate);
        return this;
    }

    public PatientBuilder deathDate(String deathDate) {
        entity.setDeathDate(parseDate(deathDate));
        return this;
    }

    public PatientBuilder causeOfDeath(Concept causeOfDeath) {
        entity.setCauseOfDeath(causeOfDeath);
        return this;
    }

    public PatientBuilder name(PersonName pn) {
        entity.addName(pn);
        return this;
    }

    public PatientBuilder address(PersonAddress pa) {
        entity.addAddress(pa);
        return this;
    }

    public PatientBuilder attribute(PersonAttribute pa) {
        entity.addAttribute(pa);
        return this;
    }

    public PatientBuilder identifier(PatientIdentifier id) {
        entity.addIdentifier(id);
        return this;
    }

    public PatientBuilder identifier(PatientIdentifierType identifierType, String identifier, Location location) {
        return identifier(new PatientIdentifier(identifier, identifierType, location));
    }

    public PatientBuilder identifier(PatientIdentifierType identifierType, String identifier) {
        return identifier(new PatientIdentifier(identifier, identifierType, null));
    }

    public PatientBuilder uuid(String uuid) {
        entity.setUuid(uuid);
        return this;
    }

    public PatientBuilder dateCreated(Date dateCreated) {
        entity.setDateCreated(dateCreated);
        return this;
    }

    public PatientBuilder dateCreated(String dateCreated) {
        entity.setDateCreated(parseDate(dateCreated));
        return this;
    }

    public PatientBuilder creator(User creator) {
        entity.setCreator(creator);
        return this;
    }

    public PatientBuilder changedBy(User by) {
        entity.setChangedBy(by);
        return this;
    }

    public PatientBuilder dateChanged(Date changed) {
        entity.setDateChanged(changed);
        return this;
    }

    public PatientBuilder dateChanged(String changed) {
        entity.setDateChanged(parseDate(changed));
        return this;
    }

    public PatientBuilder voided(boolean voided) {
        entity.setVoided(voided);
        return this;
    }

    public PatientBuilder voidedBy(User voidedBy) {
        entity.setVoidedBy(voidedBy);
        return this;
    }

    public PatientBuilder dateVoided(Date dateVoided) {
        entity.setDateCreated(dateVoided);
        return this;
    }

    public PatientBuilder dateVoided(String dateVoided) {
        entity.setDateCreated(parseDate(dateVoided));
        return this;
    }

    public PatientBuilder voidReason(String voidReason) {
        entity.setVoidReason(voidReason);
        return this;
    }

    public PatientBuilder name(String given, String family) {
        return name(new PersonName(given, null, family));
    }

    public PatientBuilder address(String... addressFields) {

        List<String> fields = Arrays.asList(addressFields);

        Iterator<String> i = fields.iterator();
        PersonAddress address = new PersonAddress();

        if (i.hasNext()) {
            address.setAddress1(i.next());
        }
        if (i.hasNext()) {
            address.setAddress2(i.next());
        }
        if (i.hasNext()) {
            address.setCityVillage(i.next()) ;
        }
        if (i.hasNext()) {
            address.setStateProvince(i.next());
        }
        if (i.hasNext()) {
            address.setPostalCode(i.next());
        }
        if (i.hasNext()) {
            address.setCountry(i.next());
        }

        entity.addAddress(address);
        return this;
    }


    public PatientBuilder age(int years) {
        entity.setBirthdateFromAge(years, null);
        return this;
    }

    public PatientBuilder female() {
        entity.setGender("F");
        return this;
    }

    public PatientBuilder male() {
        entity.setGender("M");
        return this;
    }

}
