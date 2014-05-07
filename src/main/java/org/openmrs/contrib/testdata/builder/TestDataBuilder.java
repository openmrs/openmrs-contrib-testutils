package org.openmrs.contrib.testdata.builder;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.openmrs.contrib.testdata.TestDataManager;

/**
 *
 */
public abstract class TestDataBuilder<T> {

    protected TestDataManager testDataManager;

    public TestDataBuilder(TestDataManager testDataManager) {
        this.testDataManager = testDataManager;
    }

    /**
     * Implementations must set complete = true in this method
     * @return
     */
    public abstract T save();

    /**
     * Implementations must set complete = true in this method
     * @return
     */
    public abstract T get();


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
     * Supports "yyyy-MM-dd HH:mm:ss" and "yyyy-MM-dd"
     * @param ymdMaybeHms
     * @return
     */
    protected Date parseDate(String ymdMaybeHms) {
        try {
            return DateUtils.parseDate(ymdMaybeHms, "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd");
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
