package com.example.services;

import com.example.Entity.Doctor;
import com.example.Entity.Hospital;
import com.example.exceptions.CustomException;
import com.example.exceptions.CustomExceptionMessage;
import com.example.repository.HospitalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Supplier;

@Service
public class HospitalServices {
    @Autowired
    private HospitalRepo repository;

    public HospitalServices(){}

    public HospitalServices(HospitalRepo repository) {
        this.repository = repository;
    }

    public List<Hospital> listAll() {
        return repository.findAll();
    }

    public Optional<Hospital> find(int id) {
        return repository.findById(id);
    }

    public Hospital add(Hospital hospital) {
        try {
            return repository.saveAndFlush(hospital);

        }catch (Exception e){
            throw new CustomException(CustomExceptionMessage.Error);
        }
    }

    public void delete(int id) {
        repository.deleteById(id);
    }

    public Hospital update(int id, Hospital newHospital) {
        try {
            Hospital oldHospital = repository.findById(id).get();
            oldHospital.setName(newHospital.getName());
            return repository.saveAndFlush(oldHospital);
        } catch (NoSuchElementException e) {
            throw new CustomException(CustomExceptionMessage.NO_SUCH_ELEMENT_WITH_THIS_ID);
        }
    }

    public List<Hospital> search(String name){
        try {
            return repository.search(name);
        }catch (NoSuchElementException e){
            throw new CustomException(CustomExceptionMessage.NO_SUCH_ELEMENT_WITH_THIS_ID);
        }
    }
}
