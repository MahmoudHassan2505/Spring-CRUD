package com.example.services;

import com.example.Entity.Doctor;
import com.example.Entity.Drug;
import com.example.Entity.Hospital;
import com.example.Entity.Patient;
import com.example.exceptions.CustomException;
import com.example.exceptions.CustomExceptionMessage;
import com.example.repository.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PatientServices {

    @Autowired
    PatientRepo repository;


    public List<Patient> listAll() {
        return repository.findAll();
    }

    public Optional<Patient> find(int id) {
        return repository.findById(id);
    }

    public Patient add(Patient patient) {
        return repository.saveAndFlush(patient);
    }

    public void delete(int id) {
        repository.deleteById(id);
    }

    public List<Drug> getDrugsInPatient(int id) {
        try {
            List<Drug> drugs = repository.findById(id).get().getDrugs();
            return drugs;
        } catch (NoSuchElementException e) {
            throw new CustomException(CustomExceptionMessage.NO_SUCH_ELEMENT_WITH_THIS_ID);
        }

    }

    public Patient update(int id, Patient newPatient) {
        try {
            Patient oldPatient = repository.findById(id).get();
            oldPatient.setName(newPatient.getName());
            return repository.saveAndFlush(oldPatient);
        } catch (NoSuchElementException e) {
            throw new CustomException(CustomExceptionMessage.NO_SUCH_ELEMENT_WITH_THIS_ID);
        }
    }


}
