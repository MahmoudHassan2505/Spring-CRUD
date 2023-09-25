package com.example.repository;

import com.example.Entity.Doctor;
import com.example.Entity.Hospital;
import com.example.Entity.Patient;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PatientRepoTest {

    @Autowired PatientRepo patientRepository;
    @Autowired DoctorRepo doctorRepository;
    @Autowired HospitalRepo hospitalRepository;

    Patient patient1;
    Patient patient2;
    Hospital hospital;
    Doctor doctor;


    @BeforeEach
    void arrange(){
        //arrange
        hospital = new Hospital(1,"Hospital");
        doctor = new Doctor(1,"Doctor",hospital);
        patient1 = new Patient(1,"patient 1",doctor,hospital);
        patient2 = new Patient(2,"patient 2",doctor,hospital);

        hospitalRepository.save(hospital);
        doctorRepository.save(doctor);
        patientRepository.save(patient1);
        patientRepository.save(patient2);

    }
    @Test
    @Disabled
    void Search_Return_Values() {
        //act && arrange
        Assertions.assertThat(patientRepository.search("pat")).hasSize(2);
        Assertions.assertThat(patientRepository.search("pat").get(0).getName()).isEqualTo(patient1.getName());

    }

    @Test
    @Disabled
    void Search_Return_Empty_List() {
        //act && arrange
        Assertions.assertThat(patientRepository.search("mah")).hasSize(0);

    }
}