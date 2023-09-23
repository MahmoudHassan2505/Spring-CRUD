package com.example.controllers;

import com.example.Entity.Doctor;
import com.example.Entity.Hospital;
import com.example.Entity.Patient;
import com.example.services.DoctorServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    DoctorServices services;
    @GetMapping("list")
    public List<Doctor> findAll(){
        return services.listAll();
    }

    @GetMapping("/{id}")
    public Optional<Doctor> get(@PathVariable int id){
        return services.find(id);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Doctor add(@RequestBody final Doctor doctor){
        return services.add(doctor);
    }

    @PostMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void delete(@PathVariable int id){
        services.delete(id);
    }

    @GetMapping("/{id}/patient")
    public List<Patient> getPatients(@PathVariable int id) {

        return services.listPatient(id);

    }

    @PostMapping("/update/{id}")
    public Doctor update(@PathVariable int id, @RequestBody final Doctor doctor){
        return services.update(id,doctor);
    }
}
