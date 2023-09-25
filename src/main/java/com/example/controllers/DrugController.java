package com.example.controllers;

import com.example.Entity.Doctor;
import com.example.Entity.Drug;
import com.example.Entity.Hospital;
import com.example.services.DrugServices;
import com.example.services.HospitalServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/drugs")
public class DrugController {

    @Autowired
    private DrugServices services;
    @GetMapping("/list")
    public List<Drug> listAll(){
        return services.listAll();
    }

    @GetMapping("/{id}")
    public Optional<Drug> find(@PathVariable int id){
        return services.find(id);
    }
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Drug add(@RequestBody final Drug drug){
        return services.add(drug);
    }

    @PostMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void delete(@PathVariable int id){
        services.delete(id);
    }


    @PostMapping("/update/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Drug update(@PathVariable int id,@RequestBody final Drug drug){
        return services.update(id,drug);
    }

    @GetMapping("/search")
    public List<Drug> search(@RequestParam String name){
        return services.search(name);
    }
}
