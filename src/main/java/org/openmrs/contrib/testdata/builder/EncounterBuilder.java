package org.openmrs.contrib.testdata.builder;

import org.openmrs.Encounter;
import org.openmrs.EncounterType;
import org.openmrs.Location;
import org.openmrs.Patient;
import org.openmrs.Visit;
import org.openmrs.contrib.testdata.TestDataManager;

import java.util.Date;

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

    public EncounterBuilder location(Location location) {
        entity.setLocation(location);
        return this;
    }

    public EncounterBuilder encounterDatetime(Date date) {
        entity.setEncounterDatetime(date);
        return this;
    }
}
