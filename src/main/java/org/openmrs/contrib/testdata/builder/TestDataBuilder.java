package org.openmrs.contrib.testdata.builder;

import org.apache.commons.beanutils.PropertyUtils;
import org.openmrs.contrib.testdata.TestDataManager;

/**
 *
 */
public abstract class TestDataBuilder<T> {

    protected TestDataManager testDataManager;

    public TestDataBuilder(TestDataManager testDataManager) {
        this.testDataManager = testDataManager;
    }

    public abstract T save();

    /**
     * If toBean.toProperty is null, then overwrite it with fromBean.fromProperty.
     * @param fromBean
     * @param fromProperty
     * @param toBean
     * @param toProperty
     */
    protected void maybeCopyProperty(Object fromBean, String fromProperty, Object toBean, String toProperty) {
        try {
            Object existingValue = PropertyUtils.getProperty(toBean, toProperty);
            if (existingValue == null) {
                Object newValue = PropertyUtils.getProperty(fromBean, fromProperty);
                if (newValue != null) {
                    PropertyUtils.setProperty(toBean, toProperty, newValue);
                }
            }
        }
        catch (Exception e) {
            throw new RuntimeException("Error copying " + fromBean + "." + fromProperty + " to " + toBean + "." + toProperty, e);
        }
    }

}
