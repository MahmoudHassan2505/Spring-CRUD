package com.example.services;

import com.example.Entity.Doctor;
import com.example.Entity.Drug;
import com.example.Entity.Hospital;
import com.example.exceptions.CustomException;
import com.example.exceptions.CustomExceptionMessage;
import com.example.repository.DrugRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
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

class DrugServicesTest {

    @Mock
    DrugRepo drugRepository;
    DrugServices drugServices;
    static AutoCloseable autoCloseable;

    @BeforeEach
    void init(){
        autoCloseable = MockitoAnnotations.openMocks(this);
        drugServices = new DrugServices(drugRepository);
    }

    @AfterAll
    static void destroy() throws  Exception{
        autoCloseable.close();
    }


    @Test
    void Get_All_Drugs() {
        //act
        drugServices.listAll();

        //assert
        Mockito.verify(drugRepository).findAll();

    }

    @Test
    void Find_Drug_By_Id() {
        //act
        drugServices.find(1);

        //assert
        Mockito.verify(drugRepository).findById(1);
    }

    @Test
    void Delete_Drug_By_Id() {
        //act
        drugServices.delete(1);

        //assert
        Mockito.verify(drugRepository).deleteById(1);
    }

    @Test
    void Add_Drug_Success() {
        //arrange
        Drug drug = new Drug(1,"Drug");
        Mockito.when(drugRepository.saveAndFlush(drug)).thenReturn(drug);

        //act
        drugServices.add(drug);

        //assert
        Mockito.verify(drugRepository).saveAndFlush(drug);

    }

    @Test
    void Add_Drug_Fail() {
        //arrange
        Drug drug = new Drug(1,"Drug");
        Mockito.when(drugRepository.saveAndFlush(drug)).thenThrow(new CustomException(CustomExceptionMessage.Error));

        //act && assert
        Assertions.assertThatThrownBy(() -> drugServices.add(drug)).isInstanceOf(CustomException.class);
    }

    @Test
    void Update_Success() {
        //arrange
        Drug newDrug = new Drug(1,"New Drug");
        Drug oldDrug = new Drug(1,"Old Drug");
        Mockito.when(drugRepository.findById(any(Integer.class))).thenReturn(Optional.of(oldDrug));
        Mockito.when(drugRepository.saveAndFlush(any(Drug.class))).thenReturn(newDrug);
        //act
        Drug drug = drugServices.update(1,newDrug);

        //assert
        Assertions.assertThat(drug.getName()).isEqualTo(newDrug.getName());

    }

    @Test
    void Update_Fails() {
        //arrange
        Mockito.when(drugRepository.findById(any(Integer.class))).thenThrow(new NoSuchElementException());

        //act && assert
        Assertions.assertThatThrownBy(() -> drugServices.update(1,new Drug())).isInstanceOf(CustomException.class);

    }

    @Test
    void Search_Success() {
        //arrange
        List<Drug> exceptedDrugs = new ArrayList();
        Mockito.when(drugRepository.search(any(String.class))).thenReturn(exceptedDrugs);

        //act
        List<Drug> actualDrugs =  drugServices.search("testing");

        //assert
        Assertions.assertThat(actualDrugs).isEqualTo(exceptedDrugs);
    }

    @Test
    void Search_Fails() {
        //arrange
        Mockito.when(drugRepository.search(any(String.class))).thenThrow(new NoSuchElementException());

        //act && assert
        Assertions.assertThatThrownBy(() -> drugServices.search("testing")).isInstanceOf(CustomException.class);

    }
}