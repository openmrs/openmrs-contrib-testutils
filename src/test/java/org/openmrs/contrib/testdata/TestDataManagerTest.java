package org.openmrs.contrib.testdata;

import org.junit.Test;

/**
 *
 */
public class TestDataManagerTest {

    @Test(expected = IllegalStateException.class)
    public void testThatForgettingToSaveABuilderThrowsAnError() throws Exception {
        TestDataManager manager = new TestDataManager();
        manager.patient().male();
        manager.patient().female(); // this should throw an exception because the previous builder was never saved.
    }

}
