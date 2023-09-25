package com.example.services;

import com.example.Entity.Doctor;
import com.example.Entity.Hospital;
import com.example.exceptions.CustomException;
import com.example.exceptions.CustomExceptionMessage;
import com.example.repository.HospitalRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
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

class HospitalServicesTest {

    @Mock
    HospitalRepo hospitalRepository;
    HospitalServices hospitalServices;
    static AutoCloseable autoCloseable;

    @BeforeEach
    void init(){
        autoCloseable = MockitoAnnotations.openMocks(this);
        hospitalServices = new HospitalServices(hospitalRepository);
    }

    @AfterAll
    static void destroy()throws Exception{
        autoCloseable.close();
    }

    @Test
    void Get_All_Hospitals() {
        //act
        hospitalServices.listAll();

        //assert
        Mockito.verify(hospitalRepository).findAll();

    }

    @Test
    void Find_Hospital_By_Id() {
        //act
        hospitalServices.find(1);

        //assert
        Mockito.verify(hospitalRepository).findById(1);
    }

    @Test
    void Delete_Hospital_By_Id() {
        //act
        hospitalServices.delete(1);

        //assert
        Mockito.verify(hospitalRepository).deleteById(1);
    }

    @Test
    void Add_Hospital_Success() {
        //arrange
        Hospital hospital = new Hospital(1,"Hospital");
        Mockito.when(hospitalRepository.saveAndFlush(hospital)).thenReturn(hospital);

        //act
        hospitalServices.add(hospital);

        //assert
        Mockito.verify(hospitalRepository).saveAndFlush(hospital);
    }

    @Test
    void Add_Hospital_Fail() {
        //arrange
        Hospital hospital = new Hospital(1,"Hospital");
        Mockito.when(hospitalRepository.saveAndFlush(hospital)).thenThrow(new CustomException(CustomExceptionMessage.Error));

        //act && assert
        Assertions.assertThatThrownBy(() -> hospitalServices.add(hospital)).isInstanceOf(CustomException.class);
    }

    @Test
    void Update_Success() {
        //arrange
        Hospital newHospital = new Hospital(1,"new Hospital");
        Hospital oldHospital = new Hospital(1,"old Hospital");
        Mockito.when(hospitalRepository.findById(any(Integer.class))).thenReturn(Optional.of(oldHospital));
        Mockito.when(hospitalRepository.saveAndFlush(any(Hospital.class))).thenReturn(newHospital);
        //act
        Hospital hospital = hospitalServices.update(1,newHospital);

        //assert
        Assertions.assertThat(hospital.getName()).isEqualTo(newHospital.getName());
    }

    @Test
    void Update_Fails() {
        //arrange
        Mockito.when(hospitalRepository.findById(any(Integer.class))).thenThrow(new NoSuchElementException());

        //act && assert
        Assertions.assertThatThrownBy(() -> hospitalServices.update(1,new Hospital())).isInstanceOf(CustomException.class);

    }

    @Test
    void Search_Success() {
        //arrange
        List<Hospital> exceptedHospitals = new ArrayList();
        Mockito.when(hospitalRepository.search(any(String.class))).thenReturn(exceptedHospitals);

        //act
        List<Hospital> actualHospitals =  hospitalServices.search("testing");

        //assert
        Assertions.assertThat(actualHospitals).isEqualTo(exceptedHospitals);
    }

    @Test
    void Search_Fails() {
        //arrange
        Mockito.when(hospitalRepository.search(any(String.class))).thenThrow(new NoSuchElementException());

        //act && assert
        Assertions.assertThatThrownBy(() -> hospitalServices.search("testing")).isInstanceOf(CustomException.class);

    }

}