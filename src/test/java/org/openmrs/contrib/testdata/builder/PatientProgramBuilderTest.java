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

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.openmrs.Patient;
import org.openmrs.PatientProgram;
import org.openmrs.Program;
import org.openmrs.User;
import org.openmrs.api.ProgramWorkflowService;
import org.openmrs.contrib.testdata.TestDataManager;

public class PatientProgramBuilderTest {
	
	private TestDataManager testDataManager;
	
	private ProgramWorkflowService programWorkflowService;
	
	@Before
	public void setUp() throws Exception {
		programWorkflowService = mock(ProgramWorkflowService.class);
		when(programWorkflowService.savePatientProgram(any(PatientProgram.class))).thenAnswer(new Answer<PatientProgram>() {
			
			@Override
			public PatientProgram answer(InvocationOnMock invocation) throws Throwable {
				return (PatientProgram) invocation.getArguments()[0];
			}
		});
		
		testDataManager = new TestDataManager();
		testDataManager.setProgramWorkflowService(programWorkflowService);
	}
	
	@Test
	public void testBuilder() throws Exception {
		Patient patient = new Patient(12);
		patient.setGender("M");
		
		Program program = new Program(23);
		program.setName("Test Program");
		
		Date enrollmentDate = parseDate("2013-09-01");
		Date completionDate = parseDate("2013-10-01");
		User creator = new User(17);
		
		PatientProgram patientProgram = testDataManager.patientProgram().patient(patient).program(program)
		        .dateEnrolled(enrollmentDate).dateCompleted(completionDate).creator(creator).save();
		
		assertThat(patientProgram.getPatient().getPatientId(), is(12));
		assertThat(patientProgram.getPatient().getGender(), is("M"));
		assertThat(patientProgram.getProgram().getProgramId(), is(23));
		assertThat(patientProgram.getProgram().getName(), is("Test Program"));
		assertThat(patientProgram.getDateEnrolled(), is(enrollmentDate));
		assertThat(patientProgram.getDateCompleted(), is(completionDate));
		assertThat(patientProgram.getCreator().getUserId(), is(17));
		
		assertThat(testDataManager.numEntitiesCreated(), is(1));
		verify(programWorkflowService).savePatientProgram(patientProgram);
	}
	
	private Date parseDate(String ymd) throws ParseException {
		return DateUtils.parseDate(ymd, "yyyy-MM-dd");
	}
}
