package org.openmrs.contrib.testdata.builder;

import org.openmrs.Concept;
import org.openmrs.Encounter;
import org.openmrs.Location;
import org.openmrs.Obs;
import org.openmrs.Person;
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
        complete = true;
        Obs created = testDataManager.getObsService().saveObs(entity, null);
        testDataManager.created(Obs.class, created);
        return created;
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

}
