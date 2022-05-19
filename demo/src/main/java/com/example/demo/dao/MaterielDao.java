package com.example.demo.dao;

import com.example.demo.model.Materiel;
import com.example.demo.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MaterielDao extends JpaRepository<Materiel, Integer> {

    @Query("FROM Materiel m WHERE m.marque.nom = :nomMarque")
    List<Materiel> trouveParMarque(@Param("nomMarque") String nomMarque);
}