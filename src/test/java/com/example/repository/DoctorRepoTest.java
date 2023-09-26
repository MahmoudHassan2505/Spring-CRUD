package com.example.repository;

import com.example.Entity.Doctor;
import com.example.Entity.Hospital;
import org.assertj.core.api.Assertions;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class DoctorRepoTest {
        @Autowired DoctorRepo doctorRepository;
        @Autowired HospitalRepo hospitalRepository;
    Doctor doctor1;
    Doctor doctor2;
    Hospital hospital;

        @BeforeEach
        void arrange(){
            //arrange
            hospital = new Hospital(1,"hospital");
            hospitalRepository.save(hospital);

            doctor1 = new Doctor(1,"doctor 1",hospital);
            doctor2 = new Doctor(2,"doctor 2",hospital);
            doctorRepository.save(doctor1);
            doctorRepository.save(doctor2);
        }
        @Test
    void Search_Return_Values() {
        //act
        List<Doctor> doctors = doctorRepository.search("doc");
        //assert
            Assertions.assertThat(doctorRepository.search("doc")).hasSize(2);
            Assertions.assertThat(doctorRepository.search("doc").get(1).getName()).isEqualTo(doctor2.getName());

    }

    @Test
    void Search_Return_Empty_List(){
        //act && assert
        Assertions.assertThat(doctorRepository.search("mah")).hasSize(0);
    }

}