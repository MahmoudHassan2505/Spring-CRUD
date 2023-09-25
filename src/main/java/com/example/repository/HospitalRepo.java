package com.example.repository;

import com.example.Entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HospitalRepo extends JpaRepository<Hospital,Integer> {

    @Query("SELECT h from Hospital h where "+
            "h.name like concat('%',:query, '%')")
    List<Hospital> search(String query);

}
