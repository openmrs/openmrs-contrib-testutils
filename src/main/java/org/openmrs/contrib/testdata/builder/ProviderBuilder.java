package org.openmrs.contrib.testdata.builder;

import org.openmrs.Person;
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
        return this;
    }

    public ProviderBuilder identifier(String identifier) {
        entity.setIdentifier(identifier);
        return this;
    }


}
