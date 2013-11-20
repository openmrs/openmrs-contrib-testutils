package org.openmrs.contrib.testdata.builder;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.openmrs.Patient;
import org.openmrs.api.PatientService;
import org.openmrs.contrib.testdata.TestDataManager;

import java.text.ParseException;
import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 */
public class PatientBuilderTest {

    private TestDataManager testDataManager;
    private PatientService patientService;

    @Before
    public void setUp() throws Exception {
        patientService = mock(PatientService.class);
        when(patientService.savePatient(any(Patient.class))).thenAnswer(new Answer<Patient>() {
            @Override
            public Patient answer(InvocationOnMock invocation) throws Throwable {
                return (Patient) invocation.getArguments()[0];
            }
        });

        testDataManager = new TestDataManager();
        testDataManager.setPatientService(patientService);
    }

    @Test
    public void testBuilder() throws Exception {
        Date birthdate = parseDate("1946-05-26");
        Date dateCreated = parseDate("2013-10-01");
        String uuid = "be7890be-36a4-11e3-b90a-a351ac6b1528";

        Patient patient = testDataManager.patient().name("Alice", "Waters").gender("F").birthdate(birthdate, false).dead(false).dateCreated(dateCreated).uuid(uuid).save();

        assertThat(patient.getPersonName().getGivenName(), is("Alice"));
        assertThat(patient.getPersonName().getFamilyName(), is("Waters"));
        assertThat(patient.getGender(), is("F"));
        assertThat(patient.getBirthdate(), is(birthdate));
        assertThat(patient.getDateCreated(), is(dateCreated));
        assertThat(patient.getUuid(), is(uuid));
        assertFalse(patient.isDead());

        assertThat(testDataManager.numEntitiesCreated(), is(1));
        verify(patientService).savePatient(patient);
    }

    private Date parseDate(String ymd) throws ParseException {
        return DateUtils.parseDate(ymd, "yyyy-MM-dd");
    }
}
