package com.example.demo.controller;

import com.example.demo.dao.MarqueDao;
import com.example.demo.model.Marque;
import com.example.demo.view.VueMarque;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class MarqueController {

    private MarqueDao marqueDao;

    @Autowired
    public MarqueController(MarqueDao marqueDao) {
        this.marqueDao = marqueDao;
    }

    @GetMapping("/admin/liste-marque")
    @JsonView(VueMarque.class)
    public List<Marque> listeMarque () {

        return this.marqueDao.findAll();
    }

    @GetMapping("/marque/{id}")
    @JsonView(VueMarque.class)
    public ResponseEntity<Marque> marque(@PathVariable Integer id) {

        Optional<Marque> retour = this.marqueDao.findById(id);

        if(retour.isPresent()) {
            return ResponseEntity.ok(retour.get());
        } else {
            return ResponseEntity.noContent().build();
        }

    }

    @GetMapping("marque-par-nom/{nom}")
    public Marque trouveParNom(@PathVariable("nom") String nom) {
        return marqueDao.trouveParNom(nom);
    }

    @PostMapping("/admin/marque")
    public ResponseEntity<Marque> createMarque(@RequestBody Marque marque) {

        this.marqueDao.saveAndFlush(marque);

        /*return ResponseEntity
                .created(URI.create("/marque/" + marque.getId()))
                .build();*/

        return ResponseEntity.status(HttpStatus.CREATED).body(marque);
    }

    @DeleteMapping("/admin/marque/{id}")
    public ResponseEntity<Marque> deleteMarque(@PathVariable int id){

        Optional<Marque> marqueAsupprimer = marqueDao.findById(id);

        if(marqueAsupprimer.isPresent()){
            marqueDao.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.noContent().build();
        }

        /*if(marqueDao.existsById(id)) {
            this.marqueDao.deleteById(id);
            return ResponseEntity.ok(id);
        } else {
            return ResponseEntity.noContent().build();
        }*/
    }
}
