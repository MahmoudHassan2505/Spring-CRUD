package com.example.services;

import com.example.repository.DoctorRepo;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

class DoctorServicesTest {

    @Mock
    DoctorRepo doctorRepository;
    DoctorServices doctorServices;
    AutoCloseable autoCloseable;

    @BeforeEach
    void init(){
        autoCloseable=MockitoAnnotations.openMocks(this);
        doctorServices = new DoctorServices();
    }

    @AfterAll
    void destroy()throws Exception{
        autoCloseable.close();
    }

    @Test
    void Get_All_Doctors() {
    }

    @Test
    void find() {
    }

    @Test
    void delete() {
    }

    @Test
    void add() {
    }

    @Test
    void update() {
    }

    @Test
    void search() {
    }
}