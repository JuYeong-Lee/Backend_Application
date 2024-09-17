package org.cnu.realcoding.repository;//package org.cnu.realcoding.repository;

import org.cnu.realcoding.domain.Dog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DogRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Dog> findAllDog(){
        List<Dog> list = mongoTemplate.findAll(Dog.class);
        return list;
    }
    public List<Dog> findDogByAll(String name, String ownerName, String ownerPhoneNumber) {
        Query query = new Query(new Criteria("name").is(name).and("ownerName").is(ownerName).and("ownerPhoneNumber").is(ownerPhoneNumber));
        List<Dog> list= mongoTemplate.find(query, Dog.class);
        return list;
    }
    public List<Dog> findDogByName(String name) {
        Query query = new Query(new Criteria("name").is(name));
        List<Dog> list = mongoTemplate.find(query,Dog.class);
        return list;
    }
    public List<Dog> findDogByOwnerName(String ownerName){
        Query query = new Query(new Criteria("ownerName").is(ownerName));
        List<Dog> list = mongoTemplate.find(query,Dog.class);
        return list;
    }
    public List<Dog> findDogByPhoneNumber(String number){
        Query query = new Query(new Criteria("ownerPhoneNumber").is(number));
        List<Dog> list = mongoTemplate.find(query,Dog.class);
        return list;
    }
    public void insertDog(Dog dog){
        Query query = new Query();
        query.fields().include("ownerName");
        mongoTemplate.insert(dog,"dog");
    }

    public void updateAll(String dogName, String ownerName, String ownerPhoneNumber, String newDogName, String newDogKind,String newOwnerName, String newPhoneNum){
        Update update = new Update();
        Query query = new Query();

        query.addCriteria(Criteria.where("name").is(dogName));
        query.addCriteria(Criteria.where("ownerName").is(ownerName));
        query.addCriteria(Criteria.where("ownerPhoneNumber").is(ownerPhoneNumber));

        update.set("name",newDogName);
        update.set("kind",newDogKind);
        update.set("ownerName",newOwnerName);
        update.set("ownerPhoneNumber", newPhoneNum);

        mongoTemplate.updateMulti(query,update,"dog");
    }

    public void updateKind(String name, String kind, String ownerName, String ownerPhoneNumber){
        Query query = new Query(new Criteria("name").is(name).and("ownerName").is(ownerName).and("ownerPhoneNumber").is(ownerPhoneNumber));

        Update update = new Update();
        update.set("kind", kind);

        mongoTemplate.updateFirst(query,update,"dog");
    }


    public void insertMedicalRecords(String name, String ownerName, String ownerPhoneNumber, String newMedicalRecord){
        Update update = new Update();
        Query query = new Query(new Criteria("name").is(name).and("ownerName").is(ownerName).and("ownerPhoneNumber").is(ownerPhoneNumber));
        List<Dog> list =  mongoTemplate.find(query, Dog.class);
        List<String> st = list.get(0).getMedicalRecords();
        st.add(newMedicalRecord);
        update.set("medicalRecords",st);
        mongoTemplate.updateMulti(query,update,"dog");
    }
}