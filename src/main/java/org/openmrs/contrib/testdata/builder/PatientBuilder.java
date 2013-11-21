package org.openmrs.contrib.testdata.builder;

import org.openmrs.Concept;
import org.openmrs.Patient;
import org.openmrs.PatientIdentifier;
import org.openmrs.PatientIdentifierType;
import org.openmrs.PersonAddress;
import org.openmrs.PersonAttribute;
import org.openmrs.PersonName;
import org.openmrs.User;
import org.openmrs.contrib.testdata.TestDataManager;

import java.util.Date;

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

    public PatientBuilder birthdateEstimated(boolean estimated) {
        entity.setBirthdateEstimated(estimated);
        return this;
    }

    public PatientBuilder birthdate(Date birthdate, boolean estimated) {
        entity.setBirthdate(birthdate);
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

    public PatientBuilder voidReason(String voidReason) {
        entity.setVoidReason(voidReason);
        return this;
    }

    public PatientBuilder name(String given, String family) {
        return name(new PersonName(given, null, family));
    }

    public PatientBuilder age(int years) {
        entity.setBirthdateFromAge(years, null);
        return this;
    }

}
