package com.example.services;

import com.example.Entity.Doctor;
import com.example.Entity.Hospital;
import com.example.Entity.Patient;
import com.example.exceptions.CustomException;
import com.example.exceptions.CustomExceptionMessage;
import com.example.repository.DoctorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class DoctorServices {

    @Autowired DoctorRepo repository;

    public List<Doctor> listAll(){
        return repository.findAll();
    }

    public Optional<Doctor> find(int id){
        return repository.findById(id);
    }

    public void delete(int id){
        repository.deleteById(id);
    }

    public Doctor add(Doctor doctor){
        try {
            return repository.saveAndFlush(doctor);
        }catch (Exception e){
            throw new CustomException(CustomExceptionMessage.Error);
        }

    }

    public Doctor update(int id , Doctor newDoctor) {
        try {
            Doctor oldDoctor = repository.findById(id).get();
            oldDoctor.setName(newDoctor.getName());
            return repository.saveAndFlush(oldDoctor);
        } catch (NoSuchElementException e) {
            throw new CustomException(CustomExceptionMessage.NO_SUCH_ELEMENT_WITH_THIS_ID);
        }
    }

    public List<Doctor> search(String name){
        try {
            return repository.search(name);
        }catch (NoSuchElementException e){
            throw new CustomException(CustomExceptionMessage.NO_SUCH_ELEMENT_WITH_THIS_ID);
        }
    }

}
