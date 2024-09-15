package org.cnu.realcoding.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.cnu.realcoding.domain.Dog;
import org.cnu.realcoding.exection.DogNotFoundException;
import org.cnu.realcoding.repository.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class DogManagementService {
    @Autowired
    DogRepository dogRepository;

    @Getter
    private List<Dog> dogs = new ArrayList<>();

    public List<Dog> getDogs() {
        this.dogs = dogRepository.findAllDog();
        return dogs;
    }

    public void insertDog(Dog newDog){
        dogs = dogRepository.findAllDog();
        for (Dog dog : dogs) {
            if (dog.getName().equals(newDog.getName()) && dog.getOwnerName().equals(newDog.getOwnerName()) && dog.getOwnerPhoneNumber().equals(newDog.getOwnerPhoneNumber())) {
                throw new DogNotFoundException();
            }
        }
        dogs.add(newDog);
        dogRepository.insertDog(newDog);
    }

    public List<Dog> getDogByName(String name) {
        dogs = dogRepository.findDogByName(name);
        if (!dogs.isEmpty()) {
            return dogs;
        }
        throw new DogNotFoundException();
    }

    public List<Dog> getDogByOwnerName(String ownerName){
        dogs = dogRepository.findDogByOwnerName(ownerName);
        if (!dogs.isEmpty()) {
            return dogs;
        }
        throw new DogNotFoundException();
    }

    public List<Dog> getDogByPhoneNumber(String ownerPhoneNumber){
        dogs = dogRepository.findDogByPhoneNumber(ownerPhoneNumber);
        if (!dogs.isEmpty()) {
            return dogs;
        }
        throw new DogNotFoundException();
    }

    public List<Dog> getDogByAll(String dogName, String ownerName, String ownerPhoneNumber){
        dogs = dogRepository.findDogByAll(dogName,ownerName,ownerPhoneNumber);
        if (!dogs.isEmpty()) {
            return dogs;
        }
        throw new DogNotFoundException();
    }

    public void UpdateAllInfo(String dogName, String ownerName, String ownerPhoneNumber, String newDogName, String newDogKind,String newOwnerName, String newPhoneNum){
        dogs = dogRepository.findAllDog();
        try {
            for (Dog dog : dogs) {
                if (dog.getName().equals(dogName) && dog.getOwnerName().equals(ownerName) && dog.getOwnerPhoneNumber().equals(ownerPhoneNumber)) {
                    dog.setName(newDogName);
                    dog.setKind(newDogKind);
                    dog.setOwnerName(newOwnerName);
                    dog.setOwnerPhoneNumber(newPhoneNum);
                    dogRepository.updateAll(dogName, ownerName, ownerPhoneNumber, newDogName, newDogKind, newOwnerName, newPhoneNum);
                }
            }
        } catch(DogNotFoundException e) {

        } finally {
            dogs = dogRepository.findAllDog();
        }
    }

    public void UpdateKind(String dogName, String ownerName, String ownerPhoneNumber, String newDogKind){
        dogs = dogRepository.findAllDog();
        try{
            for (Dog dog : dogs) {
                if (dog.getName().equals(dogName) && dog.getOwnerName().equals(ownerName) && dog.getOwnerPhoneNumber().equals(ownerPhoneNumber)) {
                    dog.setKind(newDogKind);
                    dogRepository.updateKind(dogName,newDogKind,ownerName,ownerPhoneNumber);
                }
            }
        }catch (DogNotFoundException e){

        } finally {
            dogs = dogRepository.findAllDog();
        }

    }

    public void InsertMedicalRecord(String name, String ownerName, String ownerPhoneNumber, String newMedicalRecord){
        dogs = dogRepository.findAllDog();
        try {
            for (Dog dog : dogs) {
                if (dog.getName().equals(name) && dog.getOwnerName().equals(ownerName) && dog.getOwnerPhoneNumber().equals(ownerPhoneNumber)){
                    dogRepository.insertMedicalRecords(name, ownerName, ownerPhoneNumber, newMedicalRecord);
                }
            }
        } catch (DogNotFoundException e) {

        } finally {
            dogs = dogRepository.findAllDog();
        }
    }
}