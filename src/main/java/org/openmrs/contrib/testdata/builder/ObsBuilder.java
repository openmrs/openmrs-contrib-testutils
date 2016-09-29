package org.openmrs.contrib.testdata.builder;

import org.openmrs.Concept;
import org.openmrs.Drug;
import org.openmrs.Encounter;
import org.openmrs.Location;
import org.openmrs.Obs;
import org.openmrs.Person;
import org.openmrs.User;
import org.openmrs.contrib.testdata.TestDataManager;

import java.util.Date;

/**
 *
 */
public class ObsBuilder extends TestDataBuilder<Obs> {

    private Obs entity = new Obs();

    public ObsBuilder(TestDataManager testDataManager) {
        super(testDataManager);
    }

    @Override
    public Obs save() {
        Obs created = testDataManager.getObsService().saveObs(entity, "unit testing");
        testDataManager.created(Obs.class, created);
        return created;
    }

    @Override
    public Obs get() {
        return entity;
    }

    public ObsBuilder person(Person p) {
        entity.setPerson(p);
        return this;
    }

    public ObsBuilder encounter (Encounter encounter){
        entity.setEncounter(encounter);
        maybeCopyProperty(encounter, "encounterDatetime", entity, "obsDatetime");
        maybeCopyProperty(encounter, "location", entity, "location");
        maybeCopyProperty(encounter, "patient", entity, "person");
        return this;
    }

    public ObsBuilder obs (Obs obs) {
        entity.addGroupMember(obs);
        return this;
    }

    public ObsBuilder obsDatetime(Date date) {
        entity.setObsDatetime(date);
        return this;
    }

    public ObsBuilder location(Location location) {
        entity.setLocation(location);
        return this;
    }
    
    public ObsBuilder concept(Concept concept) {
        entity.setConcept(concept);
        return this;
    }

    public ObsBuilder concept(String code, String sourceName) {
        return concept(testDataManager.getConceptService().getConceptByMapping(code, sourceName));
    }

    public ObsBuilder member(Obs obs) {
        entity.addGroupMember(obs);
        return this;
    }

    public ObsBuilder value(Concept valueCoded) {
        entity.setValueCoded(valueCoded);
        return this;
    }

    public ObsBuilder value(String code, String sourceName) {
        entity.setValueCoded(testDataManager.getConceptService().getConceptByMapping(code, sourceName));
        return this;
    }

    public ObsBuilder value(Number valueNumeric) {
        entity.setValueNumeric(valueNumeric.doubleValue());
        return this;
    }

    public ObsBuilder value(String valueText) {
        entity.setValueText(valueText);
        return this;
    }

    public ObsBuilder value(Date valueDatetime) {
        entity.setValueDatetime(valueDatetime);
        return this;
    }

    public ObsBuilder value(Drug valueDrug) {
        entity.setValueDrug(valueDrug);
        return this;
    }

    public ObsBuilder voided(boolean voided) {
        entity.setVoided(voided);
        return this;
    }

    public ObsBuilder voidedBy(User voidedBy) {
        entity.setVoidedBy(voidedBy);
        return this;
    }

    public ObsBuilder voidedBy(Integer userId) {
        User u = testDataManager.getUserService().getUser(userId);
        if (u == null) {
            throw new IllegalArgumentException("No user with id " + userId);
        }
        return voidedBy(u);
    }

    public ObsBuilder dateVoided(Date dateVoided) {
        entity.setDateVoided(dateVoided);
        return this;
    }

    public ObsBuilder dateVoided(String dateVoided) {
        entity.setDateVoided(parseDate(dateVoided));
        return this;
    }

    public ObsBuilder voidReason(String voidReason) {
        entity.setVoidReason(voidReason);
        return this;
    }


}
