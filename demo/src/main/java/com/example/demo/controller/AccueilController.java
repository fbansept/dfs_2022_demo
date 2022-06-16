package com.example.demo.controller;

import com.example.demo.model.Utilisateur;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

import static com.example.demo.DemoApplication.logger;


@RestController
public class AccueilController {

    @GetMapping("/")
    public String hello(){

        logger.warn("+++++++++++++++ Hello World +++++++++++++++");

        return "Serveur SPRING OK";

    }
}
