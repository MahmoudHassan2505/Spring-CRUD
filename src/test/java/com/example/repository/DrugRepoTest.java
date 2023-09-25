package com.example.repository;

import com.example.Entity.Drug;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class DrugRepoTest {

    @Autowired DrugRepo drugRepository;
    Drug drug1;
    Drug drug2;
    @BeforeEach
    void arrange(){
        //arrange
        drug1 = new Drug(1,"Drug 1");
        drug2 = new Drug(2,"Drug 2");

        drugRepository.save(drug1);
        drugRepository.save(drug2);
    }

    @Test
    @Disabled
    void Search_Return_Values() {
        //act && assert
        Assertions.assertThat(drugRepository.search("Drug")).hasSize(2);
        Assertions.assertThat(drugRepository.search("Drug").get(1).getName()).isEqualTo(drug2.getName());

    }

    @Test
    @Disabled
    void Search_Return_Empty_List() {
        //act && assert
        Assertions.assertThat(drugRepository.search("mah")).hasSize(0);

    }
}