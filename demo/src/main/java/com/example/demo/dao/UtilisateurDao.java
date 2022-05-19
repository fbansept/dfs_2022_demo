package com.example.demo.dao;

import com.example.demo.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface UtilisateurDao extends JpaRepository<Utilisateur, Integer> {

    Optional<Utilisateur> findByNom(String nom);

    @Query("FROM Utilisateur u JOIN FETCH u.listeRole WHERE u.email = :email")
    Optional<Utilisateur> findByEmailWithRoles(@Param("email") String email);

    @Query("FROM Utilisateur u WHERE u.nom = :nom")
    Optional<Utilisateur> findByNomWithRolesSansFetch(@Param("nom") String nom);

    @Query(value = "SELECT count(*) as nbUtilisateur, max(id) as maximumId FROM utilisateur",nativeQuery = true)
    Map<String,Object> compterUtilisateur();

    @Query(value = "SELECT * FROM utilisateur",nativeQuery = true)
    List<Map<String,Object>> selectUtilisateur();

}