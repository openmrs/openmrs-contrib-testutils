package org.openmrs.contrib.testdata.builder;

import org.openmrs.ConceptNumeric;
import org.openmrs.contrib.testdata.TestDataManager;

public class ConceptNumericBuilder extends ConceptBuilder {

    public ConceptNumericBuilder(TestDataManager testDataManager) {
        super(testDataManager);
        entity = new ConceptNumeric();
    }
}
