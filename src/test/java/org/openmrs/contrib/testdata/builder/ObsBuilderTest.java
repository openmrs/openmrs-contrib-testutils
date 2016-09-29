package org.openmrs.contrib.testdata.builder;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.openmrs.Concept;
import org.openmrs.Encounter;
import org.openmrs.Location;
import org.openmrs.Obs;
import org.openmrs.Person;
import org.openmrs.api.ObsService;
import org.openmrs.contrib.testdata.TestDataManager;

import java.util.Date;

public class ObsBuilderTest {

    private TestDataManager testDataManager;
    private ObsService obsService;

    @Before
    public void setUp() throws Exception {
        obsService = mock(ObsService.class);
        when(obsService.saveObs(any(Obs.class), any(String.class))).thenAnswer(new Answer<Obs>() {
            @Override
            public Obs answer(InvocationOnMock invocation) throws Throwable {
                return (Obs) invocation.getArguments()[0];
            }
        });

        testDataManager = new TestDataManager();
        testDataManager.setObsService(obsService);
    }

    @Test
    public void testBuilder() {

        Concept concept = new Concept();
        Person person = new Person();
        Encounter encounter = new Encounter();
        Obs memberObs1 = new Obs();
        Obs memberObs2 = new Obs();
        Location location = new Location();
        Date date = new Date();

        Obs obs = testDataManager.obs().value(11).concept(concept).person(person).encounter(encounter)
                .location(location).obsDatetime(date).member(memberObs1).member(memberObs2).save();

        assertThat(obs.getValueNumeric(), is(new Double(11)));
        assertThat(obs.getConcept(), is(concept));
        assertThat(obs.getPerson(), is(person));
        assertThat(obs.getEncounter(), is(encounter));
        assertThat(obs.getLocation(), is(location));
        assertThat(obs.getObsDatetime(), is(date));
        assertThat(obs.getGroupMembers().size(), is(2));
        assertThat(obs.getGroupMembers(), hasItem(memberObs1));
        assertThat(obs.getGroupMembers(), hasItem(memberObs2));

        assertThat(testDataManager.numEntitiesCreated(), is(1));
        verify(obsService).saveObs(obs, "unit testing");
    }

}
