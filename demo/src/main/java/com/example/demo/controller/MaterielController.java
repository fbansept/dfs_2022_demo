package com.example.demo.controller;

import com.example.demo.dao.MaterielDao;
import com.example.demo.model.Marque;
import com.example.demo.model.Materiel;
import com.example.demo.view.VueMateriel;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class MaterielController {

    private MaterielDao materielDao;

    @Autowired
    public MaterielController(MaterielDao materielDao) {
        this.materielDao = materielDao;
    }

    @GetMapping("/liste-materiel")
    @JsonView(VueMateriel.class)
    public List<Materiel> listeMateriel () {

        return this.materielDao.findAll();
    }


    @GetMapping("/materiel/{id}")
    @JsonView(VueMateriel.class)
    public Materiel materiel(@PathVariable Integer id) {

        List<Materiel> marque = materielDao.trouveParMarque("DELL");

        return this.materielDao.findById(id).orElse(null);

    }

    @PostMapping("/materiel")
    public String createMateriel(@RequestBody Materiel materiel) {

        this.materielDao.save(materiel);

        return "ok";
    }

    @DeleteMapping("/materiel/{id}")
    public String deleteMateriel(@PathVariable int id){

        this.materielDao.deleteById(id);

        return "ok";
    }

}
