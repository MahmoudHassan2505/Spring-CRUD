package com.example.repository;

import com.example.Entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.print.Doc;
import java.util.List;

@Repository
public interface DoctorRepo extends JpaRepository<Doctor,Integer> {
    @Query("SELECT d from Doctor d where "+
            "d.name like concat('%',:query, '%')")
    List<Doctor> search(String query);
}
