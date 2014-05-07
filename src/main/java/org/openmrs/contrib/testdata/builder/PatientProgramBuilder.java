/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.contrib.testdata.builder;

import java.util.Date;

import org.openmrs.Patient;
import org.openmrs.PatientProgram;
import org.openmrs.Program;
import org.openmrs.User;
import org.openmrs.contrib.testdata.TestDataManager;

/**
 *
 */
public class PatientProgramBuilder extends TestDataBuilder<PatientProgram> {
	
	private PatientProgram entity = new PatientProgram();
	
	public PatientProgramBuilder(TestDataManager testDataManager) {
		super(testDataManager);
	}
	
	@Override
	public PatientProgram save() {
		PatientProgram created = testDataManager.getProgramWorkflowService().savePatientProgram(entity);
		testDataManager.created(PatientProgram.class, created);
		return created;
	}

    @Override
    public PatientProgram get() {
        return entity;
    }

	public PatientProgramBuilder patient(Patient patient) {
		entity.setPatient(patient);
		return this;
	}
	
	public PatientProgramBuilder program(Program program) {
		entity.setProgram(program);
		return this;
	}
	
	public PatientProgramBuilder dateEnrolled(Date enrollmentDate) {
		entity.setDateEnrolled(enrollmentDate);
		return this;
	}
	
	public PatientProgramBuilder dateCompleted(Date completionDate) {
		entity.setDateCompleted(completionDate);
		return this;
	}
	
	public PatientProgramBuilder creator(User creator) {
		entity.setCreator(creator);
		return this;
	}
	
}
