package com.example.repository;

import com.example.Entity.Hospital;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class HospitalRepoTest {

    @Autowired HospitalRepo hospitalRepository;
    Hospital hospital1;
    Hospital hospital2;

    @BeforeEach
    void arrange(){
        //arrange
        hospital1 = new Hospital(1,"hospital 1");
        hospital2 = new Hospital(2,"hospital 2");

        hospitalRepository.save(hospital1);
        hospitalRepository.save(hospital2);

    }

    @Test
    void Search_Return_Values() {
        //act && assert
        Assertions.assertThat(hospitalRepository.search("hosp")).hasSize(2);
        Assertions.assertThat(hospitalRepository.search("hosp").get(1).getName()).isEqualTo(hospital2.getName());
    }

    @Test
    void Search_Return_Empty_List() {
        //act && assert
        Assertions.assertThat(hospitalRepository.search("mah")).hasSize(0);
    }
}