package com.example.services;

import com.example.Entity.Doctor;
import com.example.Entity.Hospital;
import com.example.Entity.Patient;
import com.example.exceptions.CustomException;
import com.example.exceptions.CustomExceptionMessage;
import com.example.repository.PatientRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class PatientServicesTest {

    @Mock
    PatientRepo patientRepository;
    PatientServices patientServices;
    static AutoCloseable autoCloseable;

    @BeforeEach
    void init(){
        autoCloseable = MockitoAnnotations.openMocks(this);
        patientServices = new PatientServices(patientRepository);
    }

    @AfterAll
    static void destroy()throws Exception{
        autoCloseable.close();
    }

    @Test
    void Get_All_Patients() {
        //act
        patientServices.listAll();

        //assert
        Mockito.verify(patientRepository).findAll();

    }

    @Test
    void Find_Patient_By_Id() {
        //act
        patientServices.find(1);

        //assert
        Mockito.verify(patientRepository).findById(1);
    }

    @Test
    void Delete_Patient_By_Id() {
        //act
        patientServices.delete(1);

        //assert
        Mockito.verify(patientRepository).deleteById(1);
    }

    @Test
    void Add_Patient_Success() {
        //arrange
        Patient patient = new Patient(1,"patient",new Doctor(),new Hospital());
        Mockito.when(patientRepository.saveAndFlush(patient)).thenReturn(patient);

        //act
        patientServices.add(patient);

        //assert
        Mockito.verify(patientRepository).saveAndFlush(patient);

    }

    @Test
    void Add_Patient_Fail() {
        //arrange
        Patient patient = new Patient(1,"patient",new Doctor(),new Hospital());
        Mockito.when(patientRepository.saveAndFlush(patient)).thenThrow(new CustomException(CustomExceptionMessage.Error));

        //act && assert
        Assertions.assertThatThrownBy(() -> patientServices.add(patient)).isInstanceOf(CustomException.class);
    }

    @Test
    void Update_Success() {
        //arrange
        Patient newPatient = new Patient(1,"new patient",new Doctor(),new Hospital());
        Patient oldPatient = new Patient(1,"old patient",new Doctor(),new Hospital());
        Mockito.when(patientRepository.findById(any(Integer.class))).thenReturn(Optional.of(oldPatient));
        Mockito.when(patientRepository.saveAndFlush(any(Patient.class))).thenReturn(newPatient);
        //act
        Patient patient = patientServices.update(1,newPatient);

        //assert
        Assertions.assertThat(patient.getName()).isEqualTo(newPatient.getName());

    }

    @Test
    void Update_Fails() {
        //arrange
        Mockito.when(patientRepository.findById(any(Integer.class))).thenThrow(new NoSuchElementException());

        //act && assert
        Assertions.assertThatThrownBy(() -> patientServices.update(1,new Patient())).isInstanceOf(CustomException.class);

    }

    @Test
    void Search_Success() {
        //arrange
        List<Patient> exceptedPatients = new ArrayList();
        Mockito.when(patientRepository.search(any(String.class))).thenReturn(exceptedPatients);

        //act
        List<Patient> actualPatients =  patientServices.search("testing");

        //assert
        Assertions.assertThat(actualPatients).isEqualTo(exceptedPatients);
    }

    @Test
    void Search_Fails() {
        //arrange
        Mockito.when(patientRepository.search(any(String.class))).thenThrow(new NoSuchElementException());

        //act && assert
        Assertions.assertThatThrownBy(() -> patientServices.search("testing")).isInstanceOf(CustomException.class);

    }

}