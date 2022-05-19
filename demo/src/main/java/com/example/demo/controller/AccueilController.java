package com.example.demo.controller;

import com.example.demo.model.Utilisateur;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class AccueilController {

    @GetMapping("/")
    public String hello(){

        return "Serveur SPRING OK";

    }
}
