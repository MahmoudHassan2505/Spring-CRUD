package com.example.repository;

import com.example.Entity.Drug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrugRepo extends JpaRepository<Drug,Integer> {
}
