package com.example.repository;

import com.example.Entity.Drug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DrugRepo extends JpaRepository<Drug,Integer> {

    @Query("SELECT d from Drug d where "+
            "d.name like concat('%',:query, '%')")
    List<Drug> search(String query);
}
