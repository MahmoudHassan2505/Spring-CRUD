package com.example.controllers;

import com.example.Entity.Doctor;
import com.example.Entity.Hospital;
import com.example.services.HospitalServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hospitals")
public class HospitalController {
    @Autowired
    private HospitalServices services;
    @GetMapping("/list")
    public List<Hospital> listAll(){
        return services.listAll();
    }

    @GetMapping("/{id}")
    public Optional<Hospital> find(@PathVariable int id){
        return services.find(id);
    }
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Hospital add(@RequestBody final Hospital hospital){
        return services.add(hospital);
    }

    @PostMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void delete(@PathVariable int id){
        services.delete(id);
    }

    @GetMapping("/{id}/doctors")
    public List<Doctor> getDoctors(@PathVariable int id){
        return services.getDoctorsInHospital(id);
    }

    @PostMapping("/update/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Hospital update(@PathVariable int id,@RequestBody final Hospital hospital){
        return services.update(id,hospital);
    }

}
