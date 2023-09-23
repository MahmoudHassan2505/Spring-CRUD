package com.example.repository;

import com.example.Entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HospitalRepo extends JpaRepository<Hospital,Integer> {

}
