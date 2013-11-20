package org.openmrs.contrib.testdata.builder;

import org.openmrs.Location;
import org.openmrs.Patient;
import org.openmrs.Visit;
import org.openmrs.contrib.testdata.TestDataManager;

import java.util.Date;

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
        Visit created = testDataManager.getVisitService().saveVisit(entity);
        testDataManager.created(Visit.class, created);
        return created;
    }

    public VisitBuilder patient(Patient patient) {
        entity.setPatient(patient);
        return this;
    }

    public VisitBuilder started(Date date) {
        entity.setStartDatetime(date);
        return this;
    }

    public VisitBuilder stopped(Date date) {
        entity.setStopDatetime(date);
        return this;
    }

    public VisitBuilder location(Location location) {
        entity.setLocation(location);
        return this;
    }

}
