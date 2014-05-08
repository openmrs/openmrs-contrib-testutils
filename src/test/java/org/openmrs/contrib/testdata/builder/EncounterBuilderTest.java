package org.openmrs.contrib.testdata.builder;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.openmrs.Concept;
import org.openmrs.Encounter;
import org.openmrs.EncounterType;
import org.openmrs.Form;
import org.openmrs.Location;
import org.openmrs.Obs;
import org.openmrs.Patient;
import org.openmrs.api.EncounterService;
import org.openmrs.contrib.testdata.TestDataManager;

public class EncounterBuilderTest {

    private TestDataManager testDataManager;
    private EncounterService encounterService;

    @Before
    public void setUp() throws Exception {
        encounterService = mock(EncounterService.class);
        when(encounterService.saveEncounter(any(Encounter.class))).thenAnswer(new Answer<Encounter>() {
            @Override
            public Encounter answer(InvocationOnMock invocation) throws Throwable {
                return (Encounter) invocation.getArguments()[0];
            }
        });

        testDataManager = new TestDataManager();
        testDataManager.setEncounterService(encounterService);
    }

    @Test
    public void testBuilder() throws Exception {
        Concept weight = new Concept();
        Concept profession = new Concept();
        Concept softwareDeveloper = new Concept();
        Patient patient = new Patient();
        Location location = new Location();
        EncounterType encounterType = new EncounterType();
		Form form = new Form();
        Date date = new Date();

        Encounter encounter = testDataManager.encounter()
                .patient(patient)
                .location(location)
                .encounterDatetime(date)
                .encounterType(encounterType)
				.form(form)
                .obs(weight, 70)
                .obs(profession, softwareDeveloper)
                .save();

        assertThat(encounter.getPatient(), is(patient));
        assertThat(encounter.getLocation(), is(location));
        assertThat(encounter.getEncounterType(), is(encounterType));
		assertThat(encounter.getForm(), is(form));
        assertThat(encounter.getEncounterDatetime(), is(date));
        assertThat(encounter.getObs(), IsIterableContainingInAnyOrder.containsInAnyOrder(
                obsLike(patient, location, date, weight, 70d),
                obsLike(patient, location, date, profession, softwareDeveloper)));

        assertThat(testDataManager.numEntitiesCreated(), is(1));
        verify(encounterService).saveEncounter(encounter);
    }

    private Matcher<Obs> obsLike(final Patient patient, final Location location, final Date date, final Concept concept, final Object value) {
        return new BaseMatcher<Obs>() {
            @Override
            public boolean matches(Object item) {
                Obs actual = (Obs) item;
                return actual.getPerson().equals(patient) && actual.getLocation().equals(location)
                        && actual.getObsDatetime().equals(date) && actual.getConcept().equals(concept)
                        && (value.equals(actual.getValueNumeric()) || value.equals(actual.getValueCoded()));
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("obs " + concept + " = " + value);
            }
        };
    }

}
