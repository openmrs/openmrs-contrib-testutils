package org.openmrs.contrib.testdata.builder;

import org.openmrs.Concept;
import org.openmrs.ConceptAnswer;
import org.openmrs.ConceptClass;
import org.openmrs.ConceptDatatype;
import org.openmrs.ConceptMap;
import org.openmrs.ConceptMapType;
import org.openmrs.ConceptName;
import org.openmrs.ConceptReferenceTerm;
import org.openmrs.ConceptSource;
import org.openmrs.api.context.Context;
import org.openmrs.contrib.testdata.TestDataManager;

import java.util.Locale;

public class ConceptBuilder extends TestDataBuilder<Concept> {

    private Concept entity = new Concept();

    public ConceptBuilder(TestDataManager testDataManager) {
        super(testDataManager);
    }

    @Override
    public Concept save() {
        Concept created = testDataManager.getConceptService().saveConcept(entity);
        testDataManager.created(Concept.class, created);
        return created;
    }

    @Override
    public Concept get() {
        return entity;
    }

    public ConceptBuilder uuid(String uuid) {
        entity.setUuid(uuid);
        return this;
    }

    public ConceptBuilder datatype(String datatypeName) {
        return datatype(testDataManager.getConceptService().getConceptDatatypeByName(datatypeName));
    }

    public ConceptBuilder datatype(ConceptDatatype conceptDatatype) {
        entity.setDatatype(conceptDatatype);
        return this;
    }

    public ConceptBuilder conceptClass(String className) {
        return conceptClass(testDataManager.getConceptService().getConceptClassByName(className));
    }

    public ConceptBuilder conceptClass(ConceptClass conceptClass) {
        entity.setConceptClass(conceptClass);
        return this;
    }

    public ConceptBuilder name(String name) {
        return name(Context.getLocale(), name);
    }

    public ConceptBuilder name(Locale locale, String name) {
        entity.addName(new ConceptName(name, locale));
        return this;
    }

    public ConceptBuilder mapping(String mapTypeName, String sourceColonCode) {
        String[] split = sourceColonCode.split(":");
        String sourceName = split[0];
        String code = split[1];
        ConceptSource source = testDataManager.getConceptService().getConceptSourceByName(sourceName);
        if (source == null) {
            source = new ConceptSource();
            source.setName(sourceName);
            source = testDataManager.getConceptService().saveConceptSource(source);
        }
        ConceptReferenceTerm term = testDataManager.getConceptService().getConceptReferenceTermByCode(code, source);
        if (term == null) {
            term = new ConceptReferenceTerm();
            term.setConceptSource(source);
            term.setCode(code);
            term = testDataManager.getConceptService().saveConceptReferenceTerm(term);
        }
        ConceptMapType mapType = testDataManager.getConceptService().getConceptMapTypeByName(mapTypeName);
        if (mapType == null) {
            mapType = new ConceptMapType();
            mapType.setName(mapTypeName);
            mapType.setIsHidden(false);
            mapType = testDataManager.getConceptService().saveConceptMapType(mapType);
        }
        entity.addConceptMapping(new ConceptMap(term, mapType));
        return this;
    }

    public ConceptBuilder setMember(Concept c) {
        entity.addSetMember(c);
        return this;
    }

    public ConceptBuilder setMembers(Concept... members) {
        for (Concept member : members) {
            setMember(member);
        }
        return this;
    }

    public ConceptBuilder answer(Concept c) {
        entity.addAnswer(new ConceptAnswer(c));
        return this;
    }

    public ConceptBuilder answers(Concept... answers) {
        for (Concept answer : answers) {
            answer(answer);
        }
        return this;
    }

}
