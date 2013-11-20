package org.openmrs.contrib.testdata;

import org.openmrs.api.ConceptService;
import org.openmrs.api.EncounterService;
import org.openmrs.api.ObsService;
import org.openmrs.api.PatientService;
import org.openmrs.api.VisitService;
import org.openmrs.contrib.testdata.builder.EncounterBuilder;
import org.openmrs.contrib.testdata.builder.ObsBuilder;
import org.openmrs.contrib.testdata.builder.PatientBuilder;
import org.openmrs.contrib.testdata.builder.VisitBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TestDataManager {

    List<Object> entitiesCreated = new ArrayList<Object>();

    @Autowired
    private PatientService patientService;

    @Autowired
    private VisitService visitService;

    @Autowired
    private EncounterService encounterService;

    @Autowired
    private ObsService obsService;

    @Autowired
    private ConceptService conceptService;

    public TestDataManager() {
    }

    public PatientService getPatientService() {
        return patientService;
    }

    public void setPatientService(PatientService patientService) {
        this.patientService = patientService;
    }

    public VisitService getVisitService() {
        return visitService;
    }

    public void setVisitService(VisitService visitService) {
        this.visitService = visitService;
    }

    public EncounterService getEncounterService() {
        return encounterService;
    }

    public void setEncounterService(EncounterService encounterService) {
        this.encounterService = encounterService;
    }

    public ObsService getObsService() {
        return obsService;
    }

    public void setObsService(ObsService obsService) {
        this.obsService = obsService;
    }

    public ConceptService getConceptService() {
        return conceptService;
    }

    public void setConceptService(ConceptService conceptService) {
        this.conceptService = conceptService;
    }

    public PatientBuilder patient() {
        return new PatientBuilder(this);
    }

    public VisitBuilder visit() {
        return new VisitBuilder(this);
    }

    public EncounterBuilder encounter() {
        return new EncounterBuilder(this);
    }

    public ObsBuilder obs() {
        return new ObsBuilder(this);
    }

    public <T, U extends T> void created(Class<T> clazz, U created) {
        // TODO store these in a more useful way, e.g. by clazz
        entitiesCreated.add(created);
    }

    public int numEntitiesCreated() {
        return entitiesCreated.size();
    }

}
