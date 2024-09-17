package org.cnu.realcoding.controller;

import org.cnu.realcoding.domain.Dog;
import org.cnu.realcoding.service.DogManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DogController {

    @Autowired
    private DogManagementService dogManagementService;

    @PostMapping("/dogs")
    @ResponseStatus(HttpStatus.CREATED)
    public void createDogs(@RequestBody Dog dog){
        dogManagementService.insertDog(dog);
    }

    @GetMapping("/dogs")
    public List<Dog> getAllDogs(){
        return dogManagementService.getDogs();
    }

    // localhost:8080/dogs/xxx
    @GetMapping("/dogs/{name}")
    public List<Dog> getDogByName(@RequestParam(value="name") String name){
        return dogManagementService.getDogByName(name);
    }

    @GetMapping("/dogs/{ownerName}")
    public List<Dog> getDogByOwnerName(@RequestParam(value="ownerName") String ownerName){
        return dogManagementService.getDogByOwnerName(ownerName);
    }

    @GetMapping("/dogs/{ownerPhoneNumber}")
    public List<Dog> getDogByPhoneNumber(@RequestParam(value="ownerPhoneNumber") String ownerPhoneNumber){
        return dogManagementService.getDogByPhoneNumber(ownerPhoneNumber);
    }

    @GetMapping("/dogs/all")
    public List<Dog> getDogByAll(@RequestParam(value="name") String name, @RequestParam(value="ownerName") String ownerName, @RequestParam(value="ownerPhoneNumber")String ownerPhoneNumber){
        return dogManagementService.getDogByAll(name, ownerName, ownerPhoneNumber);
    }

    @PutMapping("/dogs/updateAll")
    public void UpdateAllInfo(@RequestParam(value="name") String name, @RequestParam(value="ownerName") String ownerName, @RequestParam(value="ownerPhoneNumber") String ownerPhoneNumber, @RequestParam(value="newDogName") String newDogName, @RequestParam(value="newDogKind") String newDogKind, @RequestParam(value="newOwnerName") String newOwnerName, @RequestParam(value="newPhoneNum") String newPhoneNum){
        dogManagementService.UpdateAllInfo(name, ownerName, ownerPhoneNumber, newDogName, newDogKind, newOwnerName, newPhoneNum);
    }

    @PatchMapping("/dogs/updateKind")
    public void UpdateKind(@RequestParam(value="name") String name, @RequestParam(value="ownerName") String ownerName, @RequestParam(value="ownerPhoneNumber") String ownerPhoneNumber, @RequestParam(value="newDogKind") String newDogKind){
        dogManagementService.UpdateKind(name, ownerName, ownerPhoneNumber, newDogKind);
    }

    @PostMapping("/dogs/medical")
    public void CreateMedicalRecord(@RequestParam(value="name") String name, @RequestParam(value="ownerName") String ownerName, @RequestParam(value="ownerPhoneNumber") String ownerPhoneNumber, @RequestParam(value="medicalRecord") String medicalRecord){
        dogManagementService.InsertMedicalRecord(name, ownerName, ownerPhoneNumber, medicalRecord);
    }
}

