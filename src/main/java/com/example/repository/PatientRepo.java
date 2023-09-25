package com.example.repository;

import com.example.Entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepo extends JpaRepository<Patient,Integer> {

    @Query("SELECT p from Patient p where "+
            "p.name like concat('%',:query, '%')")
    List<Patient> search(String query);
}
