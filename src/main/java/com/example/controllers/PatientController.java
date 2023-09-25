package com.example.controllers;

import com.example.Entity.Doctor;
import com.example.Entity.Drug;
import com.example.Entity.Patient;
import com.example.services.PatientServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    PatientServices services;

    @GetMapping("list")
    public List<Patient> findAll(){
        return services.listAll();
    }

    @GetMapping("/{id}")
    public Optional<Patient> get(@PathVariable int id){
        return services.find(id);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Patient add(@RequestBody final Patient patient){
        return services.add(patient);
    }

    @PostMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void delete(@PathVariable int id){
        services.delete(id);
    }

    @PostMapping("/update/{id}")
    public Patient update(@PathVariable int id, @RequestBody final Patient patient){
        return services.update(id,patient);
    }

    @GetMapping("/search")
    public List<Patient> search(@RequestParam String name){
        return services.search(name);
    }
}
