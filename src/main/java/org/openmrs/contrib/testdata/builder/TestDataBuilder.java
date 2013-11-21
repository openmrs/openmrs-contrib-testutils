package org.openmrs.contrib.testdata.builder;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.openmrs.contrib.testdata.TestDataManager;

import java.text.ParseException;
import java.util.Date;

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

    /**
     * Supports "yyyy-MM-dd" and "yyyy-MM-dd hh:mm:ss"
     * @param ymdMaybeHms
     * @return
     */
    protected Date parseDate(String ymdMaybeHms) {
        try {
            return DateUtils.parseDate(ymdMaybeHms, "yyyy-MM-dd", "yyyy-MM-dd hh:mm:ss");
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
