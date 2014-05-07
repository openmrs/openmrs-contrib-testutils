package org.openmrs.contrib.testdata.builder;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.openmrs.Encounter;
import org.openmrs.Location;
import org.openmrs.Patient;
import org.openmrs.User;
import org.openmrs.Visit;
import org.openmrs.VisitType;
import org.openmrs.api.context.Context;
import org.openmrs.contrib.testdata.TestDataManager;

/**
 *
 */
public class VisitBuilder extends TestDataBuilder<Visit> {

    Visit entity = new Visit();

    public VisitBuilder(TestDataManager testDataManager) {
        super(testDataManager);
    }

    @Override
    public Visit save() {
        Visit created = Context.getVisitService().saveVisit(entity);
        testDataManager.created(Visit.class, created);
        return created;
    }

    @Override
    public Visit get() {
        return entity;
    }

    public VisitBuilder encounter(Encounter encounter) {

        encounter.setVisit(entity);
        encounter.setPatient(entity.getPatient());

        if (entity.getEncounters() == null) {
            Set<Encounter> encounters = new HashSet<Encounter>();
            encounters.add(encounter);
            entity.setEncounters(encounters);
        }
        else {
            entity.getEncounters().add(encounter);
        }

        return this;
    }

    public VisitBuilder patient(Patient patient) {
        entity.setPatient(patient);
        return this;
    }

    public VisitBuilder started(Date date) {
        entity.setStartDatetime(date);
        return this;
    }

    public VisitBuilder started(String date) {
        entity.setStartDatetime(parseDate(date));
        return this;
    }

    public VisitBuilder stopped(Date date) {
        entity.setStopDatetime(date);
        return this;
    }

    public VisitBuilder stopped(String date) {
        entity.setStopDatetime(parseDate(date));
        return this;
    }

    public VisitBuilder location(Location location) {
        entity.setLocation(location);
        return this;
    }

    public VisitBuilder visitType(VisitType visitType) {
        entity.setVisitType(visitType);
        return this;
    }

    public VisitBuilder dateCreated(Date dateCreated) {
        entity.setDateCreated(dateCreated);
        return this;
    }

    public VisitBuilder dateCreated(String dateCreated) {
        entity.setDateCreated(parseDate(dateCreated));
        return this;
    }

    public VisitBuilder creator(User creator) {
        entity.setCreator(creator);
        return this;
    }

    public VisitBuilder changedBy(User by) {
        entity.setChangedBy(by);
        return this;
    }

    public VisitBuilder dateChanged(Date changed) {
        entity.setDateChanged(changed);
        return this;
    }

    public VisitBuilder dateChanged(String changed) {
        entity.setDateChanged(parseDate(changed));
        return this;
    }

    public VisitBuilder voided(boolean voided) {
        entity.setVoided(voided);
        return this;
    }

    public VisitBuilder voidedBy(User voidedBy) {
        entity.setVoidedBy(voidedBy);
        return this;
    }

    public VisitBuilder dateVoided(Date dateVoided) {
        entity.setDateCreated(dateVoided);
        return this;
    }

    public VisitBuilder dateVoided(String dateVoided) {
        entity.setDateCreated(parseDate(dateVoided));
        return this;
    }

    public VisitBuilder voidReason(String voidReason) {
        entity.setVoidReason(voidReason);
        return this;
    }

}
