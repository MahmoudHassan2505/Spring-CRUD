package com.example.services;

import com.example.Entity.Doctor;
import com.example.Entity.Hospital;
import com.example.exceptions.CustomException;
import com.example.exceptions.CustomExceptionMessage;
import com.example.repository.DoctorRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class DoctorServicesTest {

    @Mock
    DoctorRepo doctorRepository;
    DoctorServices doctorServices;
    static AutoCloseable autoCloseable;

    @BeforeEach
    void init(){
        autoCloseable=MockitoAnnotations.openMocks(this);
        doctorServices = new DoctorServices(doctorRepository);
    }

    @AfterAll
    static void  destroy()throws Exception{
        autoCloseable.close();
    }

    @Test
    void Get_All_Doctors() {
        //act
        doctorServices.listAll();

        //assert
        Mockito.verify(doctorRepository).findAll();

    }

    @Test
    void Find_Doctor_By_Id() {
        //act
        doctorServices.find(1);

        //assert
        Mockito.verify(doctorRepository).findById(1);
    }

    @Test
    void Delete_Doctor_By_Id() {
        //act
        doctorServices.delete(1);

        //assert
        Mockito.verify(doctorRepository).deleteById(1);
    }

    @Test
    void Add_Doctor_Success() {
        //arrange
        Doctor doctor = new Doctor(1,"Doctor",new Hospital(1,"hospital"));
        Mockito.when(doctorRepository.saveAndFlush(doctor)).thenReturn(doctor);

        //act
        doctorServices.add(doctor);

        //assert
        Mockito.verify(doctorRepository).saveAndFlush(doctor);

    }

    @Test
    void Add_Doctor_Fail() {
        //arrange
        Doctor doctor = new Doctor(1,"Doctor",new Hospital(1,"hospital"));
        Mockito.when(doctorRepository.saveAndFlush(doctor)).thenThrow(new CustomException(CustomExceptionMessage.Error));

        //act && assert
        Assertions.assertThatThrownBy(() -> doctorServices.add(doctor)).isInstanceOf(CustomException.class);
    }

    @Test
    void Update_Success() {
        //arrange
        Doctor newDoctor = new Doctor(1,"old Doctor",new Hospital(1,"hospital"));
        Doctor oldDoctor = new Doctor(1,"new Doctor",new Hospital(1,"hospital"));
        Mockito.when(doctorRepository.findById(any(Integer.class))).thenReturn(Optional.of(oldDoctor));
        Mockito.when(doctorRepository.saveAndFlush(any(Doctor.class))).thenReturn(newDoctor);
        //act
        Doctor doctor = doctorServices.update(1,newDoctor);

        //assert
        Assertions.assertThat(doctor.getName()).isEqualTo(newDoctor.getName());

    }

    @Test
    void Update_Fails() {
        //arrange
        Mockito.when(doctorRepository.findById(any(Integer.class))).thenThrow(new NoSuchElementException());

        //act && assert
        Assertions.assertThatThrownBy(() -> doctorServices.update(1,new Doctor())).isInstanceOf(CustomException.class);

    }

    @Test
    void Search_Success() {
        //arrange
        List<Doctor> exceptedDoctors = new ArrayList();
        Mockito.when(doctorRepository.search(any(String.class))).thenReturn(exceptedDoctors);

        //act
      List<Doctor> actualDoctors =  doctorServices.search("testing");

        //assert
        Assertions.assertThat(actualDoctors).isEqualTo(exceptedDoctors);
    }

    @Test
    void Search_Fails() {
        //arrange
        Mockito.when(doctorRepository.search(any(String.class))).thenThrow(new NoSuchElementException());

        //act && assert
       Assertions.assertThatThrownBy(() -> doctorServices.search("testing")).isInstanceOf(CustomException.class);

    }
}