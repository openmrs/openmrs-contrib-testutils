package org.openmrs.contrib.testdata.builder;

import java.util.Date;

import org.openmrs.Encounter;
import org.openmrs.EncounterRole;
import org.openmrs.EncounterType;
import org.openmrs.Location;
import org.openmrs.Obs;
import org.openmrs.Patient;
import org.openmrs.Provider;
import org.openmrs.User;
import org.openmrs.Visit;
import org.openmrs.contrib.testdata.TestDataManager;

/**
 *
 */
public class EncounterBuilder extends TestDataBuilder<Encounter> {

    private Encounter entity = new Encounter();

    public EncounterBuilder(TestDataManager testDataManager) {
        super(testDataManager);
    }

    @Override
    public Encounter save() {
        Encounter created = testDataManager.getEncounterService().saveEncounter(entity);
        testDataManager.created(Encounter.class, created);
        return created;
    }

    @Override
    public Encounter get() {
        return entity;
    }

    public EncounterBuilder obs(Obs obs) {

        obs.setEncounter(entity);
        obs.setPerson(entity.getPatient());

        maybeCopyProperty(entity, "encounterDatetime", obs, "obsDatetime");

        entity.addObs(obs);

        return this;
    }

    public EncounterBuilder patient(Patient patient) {
        entity.setPatient(patient);
        return this;
    }

    public EncounterBuilder visit(Visit visit) {
        entity.setVisit(visit);
        if (visit.getPatient() != null) {
            entity.setPatient(visit.getPatient());
        }
        return this;
    }

    public EncounterBuilder encounterType(EncounterType encounterType) {
        entity.setEncounterType(encounterType);
        return this;
    }

    public EncounterBuilder provider(EncounterRole role, Provider provider) {
        entity.addProvider(role, provider);
        return this;
    }
    
    public EncounterBuilder location(Location location) {
        entity.setLocation(location);
        return this;
    }

    public EncounterBuilder encounterDatetime(Date date) {
        entity.setEncounterDatetime(date);
        return this;
    }

    public EncounterBuilder encounterDatetime(String date) {
        entity.setEncounterDatetime(parseDate(date));
        return this;
    }

    public EncounterBuilder dateCreated(Date dateCreated) {
        entity.setDateCreated(dateCreated);
        return this;
    }

    public EncounterBuilder dateCreated(String dateCreated) {
        entity.setDateCreated(parseDate(dateCreated));
        return this;
    }

    public EncounterBuilder creator(User creator) {
        entity.setCreator(creator);
        return this;
    }

    public EncounterBuilder changedBy(User by) {
        entity.setChangedBy(by);
        return this;
    }

    public EncounterBuilder dateChanged(Date changed) {
        entity.setDateChanged(changed);
        return this;
    }

    public EncounterBuilder dateChanged(String changed) {
        entity.setDateChanged(parseDate(changed));
        return this;
    }

    public EncounterBuilder voided(boolean voided) {
        entity.setVoided(voided);
        return this;
    }

    public EncounterBuilder voidedBy(User voidedBy) {
        entity.setVoidedBy(voidedBy);
        return this;
    }

    public EncounterBuilder dateVoided(Date dateVoided) {
        entity.setDateCreated(dateVoided);
        return this;
    }

    public EncounterBuilder dateVoided(String dateVoided) {
        entity.setDateCreated(parseDate(dateVoided));
        return this;
    }

    public EncounterBuilder voidReason(String voidReason) {
        entity.setVoidReason(voidReason);
        return this;
    }

}
