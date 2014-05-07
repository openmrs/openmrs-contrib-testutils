/*
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */

package org.openmrs.contrib.testdata.builder;

import org.openmrs.Person;
import org.openmrs.PersonName;
import org.openmrs.User;
import org.openmrs.contrib.testdata.TestDataManager;

public class UserBuilder extends TestDataBuilder<User> {

    private User entity = new User();

    private String password = "Fake&Password!1";

    public UserBuilder(TestDataManager testDataManager) {
        super(testDataManager);
    }

    @Override
    public User save() {
        complete = true;
        User created = testDataManager.getUserService().saveUser(entity, password);
        testDataManager.created(User.class, created);
        return created;
    }

    public UserBuilder personName(String given, String middle, String family) {
        ensurePerson();
        entity.addName(new PersonName(given, middle, family));
        return this;
    }

    public UserBuilder username(String username) {
        entity.setUsername(username);
        return this;
    }

    public UserBuilder gender(String gender) {
        ensurePerson();
        entity.getPerson().setGender(gender);
        return this;
    }

    public UserBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    private void ensurePerson() {
        if (entity.getPerson() == null) {
            entity.setPerson(new Person());
        }
    }

}
