package com.example.demo.dao;

import com.example.demo.model.Marque;
import com.example.demo.model.Materiel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MarqueDao extends JpaRepository<Marque, Integer> {
    @Query("FROM Marque WHERE nom = :nom")
    Marque trouveParNom(@Param("nom") String nom);
}