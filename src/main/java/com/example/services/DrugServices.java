package com.example.services;

import com.example.Entity.Drug;
import com.example.Entity.Patient;
import com.example.exceptions.CustomException;
import com.example.exceptions.CustomExceptionMessage;
import com.example.repository.DrugRepo;
import com.example.repository.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class DrugServices {

    @Autowired
    DrugRepo repository;


    public List<Drug> listAll() {
        return repository.findAll();
    }

    public Optional<Drug> find(int id) {
        return repository.findById(id);
    }

    public Drug add(Drug drug) {
        return repository.saveAndFlush(drug);
    }

    public void delete(int id) {
        repository.deleteById(id);
    }


    public Drug update(int id, Drug newDrug) {
        try {
            Drug oldDrug = repository.findById(id).get();
            oldDrug.setName(newDrug.getName());
            return repository.saveAndFlush(oldDrug);
        } catch (NoSuchElementException e) {
            throw new CustomException(CustomExceptionMessage.NO_SUCH_ELEMENT_WITH_THIS_ID);
        }
    }
}
