package org.openmrs.contrib.testdata.builder;

import org.openmrs.Person;
import org.openmrs.PersonName;
import org.openmrs.Provider;
import org.openmrs.api.ProviderService;
import org.openmrs.contrib.testdata.TestDataManager;

public class ProviderBuilder extends TestDataBuilder<Provider> {

    private Provider entity = new Provider();

    public ProviderBuilder(TestDataManager testDataManager) {
        super(testDataManager);
    }

    @Override
    public Provider save() {
        if (entity.getPerson() != null && entity.getPerson().getId() == null) {
            testDataManager.getPersonService().savePerson(entity.getPerson());
        }
        Provider created = ((ProviderService) testDataManager.getProviderService()).saveProvider(entity);
        testDataManager.created(Provider.class, created);
        return created;
    }

    @Override
    public Provider get() {
        return entity;
    }

    public ProviderBuilder person(Person person) {
        entity.setPerson(person);
        return this;
    }

    public ProviderBuilder name(String name) {
        entity.setName(name);
        Person person = new Person();
        person.setGender("M");
        PersonName pn = new PersonName();
        pn.setFamilyName(name);
        pn.setGivenName(name);
        person.addName(pn);
        entity.setPerson(person);
        return this;
    }

    public ProviderBuilder identifier(String identifier) {
        entity.setIdentifier(identifier);
        return this;
    }


    public ProviderBuilder personName(String given, String middle, String family) {
        ensurePerson();
        entity.getPerson().addName(new PersonName(given, middle, family));
        return this;
    }

    private void ensurePerson() {
        if (entity.getPerson() == null) {
            entity.setPerson(new Person());
        }
    }

    public ProviderBuilder gender(String gender) {
        ensurePerson();
        entity.getPerson().setGender(gender);
        return this;
    }

}
