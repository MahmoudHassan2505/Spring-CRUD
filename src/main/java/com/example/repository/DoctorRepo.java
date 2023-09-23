package com.example.repository;

import com.example.Entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.print.Doc;

@Repository
public interface DoctorRepo extends JpaRepository<Doctor,Integer> {
}
