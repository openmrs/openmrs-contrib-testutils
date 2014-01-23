package org.openmrs.contrib.testdata.builder;

import org.openmrs.Encounter;
import org.openmrs.EncounterRole;
import org.openmrs.EncounterType;
import org.openmrs.Location;
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
        complete = true;
        Encounter created = testDataManager.getEncounterService().saveEncounter(entity);
        testDataManager.created(Encounter.class, created);
        return created;
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
