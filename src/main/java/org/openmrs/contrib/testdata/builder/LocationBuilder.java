package org.openmrs.contrib.testdata.builder;

import org.openmrs.Location;
import org.openmrs.LocationTag;
import org.openmrs.contrib.testdata.TestDataManager;

public class LocationBuilder extends TestDataBuilder<Location> {

    private Location entity = new Location();

    public LocationBuilder(TestDataManager testDataManager) {
        super(testDataManager);
    }

    @Override
    public Location save() {
        Location created = testDataManager.getLocationService().saveLocation(entity);
        testDataManager.created(Location.class, created);
        return created;
    }

    @Override
    public Location get() {
        return entity;
    }

    public LocationBuilder name(String name) {
        entity.setName(name);
        return this;
    }

    public LocationBuilder tag(LocationTag tag) {
        entity.addTag(tag);
        return this;
    }

    public LocationBuilder tag(String tagName) {
        LocationTag tag = testDataManager.getLocationService().getLocationTagByName(tagName);
        if (tag != null) {
            return tag(tag);
        }
        else {
            tag = new LocationTag();
            tag.setName(tagName);
            testDataManager.getLocationService().saveLocationTag(tag);
            return tag(tag);
        }
    }
}
