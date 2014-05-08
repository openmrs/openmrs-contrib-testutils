package org.openmrs.contrib.testdata.builder;

import org.openmrs.Concept;
import org.openmrs.ConceptName;
import org.openmrs.Encounter;
import org.openmrs.EncounterRole;
import org.openmrs.EncounterType;
import org.openmrs.Form;
import org.openmrs.Location;
import org.openmrs.Obs;
import org.openmrs.Patient;
import org.openmrs.Provider;
import org.openmrs.User;
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

    public EncounterBuilder patient(Integer patientId) {
        Patient p = testDataManager.getPatientService().getPatient(patientId);
        if (p == null) {
            throw new IllegalArgumentException("No patient with id " + patientId);
        }
        return patient(p);
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

    public EncounterBuilder encounterType(Integer encounterTypeId) {
        EncounterType et = testDataManager.getEncounterService().getEncounterType(encounterTypeId);
        if (et == null) {
            throw new IllegalArgumentException("No EncounterType with id " + encounterTypeId);
        }
        return encounterType(et);
    }

    public EncounterBuilder encounterType(String nameOrUuid) {
        EncounterType et = testDataManager.getEncounterService().getEncounterType(nameOrUuid);
        if (et == null) {
            et = testDataManager.getEncounterService().getEncounterTypeByUuid(nameOrUuid);
        }
        if (et == null) {
            throw new IllegalArgumentException("No EncounterType with name or uuid " + nameOrUuid);
        }
        return encounterType(et);
    }

	public EncounterBuilder form(Form form) {
		entity.setForm(form);
		return this;
	}

	public EncounterBuilder form(Integer formId) {
		Form form = testDataManager.getFormService().getForm(formId);
		if (form == null) {
			throw new IllegalArgumentException("No Form with id " + formId);
		}
		return form(form);
	}

	public EncounterBuilder form(String nameOrUuid) {
		Form form = testDataManager.getFormService().getForm(nameOrUuid);
		if (form == null) {
			form = testDataManager.getFormService().getFormByUuid(nameOrUuid);
		}
		if (form == null) {
			throw new IllegalArgumentException("No Form with name or uuid " + nameOrUuid);
		}
		return form(form);
	}

    public EncounterBuilder provider(EncounterRole role, Provider provider) {
        entity.addProvider(role, provider);
        return this;
    }
    
    public EncounterBuilder location(Location location) {
        entity.setLocation(location);
        return this;
    }

    public EncounterBuilder location(Integer locationId) {
        Location loc = testDataManager.getLocationService().getLocation(locationId);
        if (loc == null) {
            throw new IllegalArgumentException("No Location with id " + locationId);
        }
        return location(loc);
    }

    public EncounterBuilder location(String nameOrUuid) {
        Location loc = testDataManager.getLocationService().getLocation(nameOrUuid);
        if (loc == null) {
            loc = testDataManager.getLocationService().getLocationByUuid(nameOrUuid);
        }
        if (loc == null) {
            throw new IllegalArgumentException("No Location with name or uuid " + nameOrUuid);
        }
        return location(loc);
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

    public EncounterBuilder creator(Integer userId) {
        User u = testDataManager.getUserService().getUser(userId);
        if (u == null) {
            throw new IllegalArgumentException("No user with id " + userId);
        }
        return creator(u);
    }

    public EncounterBuilder changedBy(User by) {
        entity.setChangedBy(by);
        return this;
    }

    public EncounterBuilder changedBy(Integer userId) {
        User u = testDataManager.getUserService().getUser(userId);
        if (u == null) {
            throw new IllegalArgumentException("No user with id " + userId);
        }
        return changedBy(u);
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

    public EncounterBuilder voidedBy(Integer userId) {
        User u = testDataManager.getUserService().getUser(userId);
        if (u == null) {
            throw new IllegalArgumentException("No user with id " + userId);
        }
        return voidedBy(u);
    }

    public EncounterBuilder dateVoided(Date dateVoided) {
        entity.setDateVoided(dateVoided);
        return this;
    }

    public EncounterBuilder dateVoided(String dateVoided) {
        entity.setDateVoided(parseDate(dateVoided));
        return this;
    }

    public EncounterBuilder voidReason(String voidReason) {
        entity.setVoidReason(voidReason);
        return this;
    }

    public EncounterBuilder obs(String code, String sourceName, Object value) {
        return obs(testDataManager.getConceptService().getConceptByMapping(code, sourceName), value);
    }

    public EncounterBuilder obs(Concept concept, Object value) {
        Obs obs = new Obs();
        // I think person, obsDatetime, and location, are propagated via Encounter.addObs
        // obs.setPerson(entity.getPatient());
        // obs.setObsDatetime(entity.getEncounterDatetime());
        // obs.setLocation(entity.getLocation());
        if (value instanceof Concept) {
            obs.setValueCoded((Concept) value);
        }
        else if (value instanceof ConceptName) {
            obs.setValueCodedName((ConceptName) value);
            obs.setValueCoded(((ConceptName) value).getConcept());
        }
        else if (value instanceof String) {
            obs.setValueText((String) value);
        }
        else if (value instanceof Double) {
            obs.setValueNumeric((Double) value);
        }
        else if (value instanceof Number) {
            obs.setValueNumeric(((Number) value).doubleValue());
        }
        else if (value instanceof Date) {
            obs.setValueDatetime((Date) value);
        }
        obs.setConcept(concept);
        entity.addObs(obs);
        return this;
    }

}
