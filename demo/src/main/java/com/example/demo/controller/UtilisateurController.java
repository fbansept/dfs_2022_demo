package com.example.demo.controller;

import com.example.demo.dao.UtilisateurDao;
import com.example.demo.model.Marque;
import com.example.demo.model.Role;
import com.example.demo.model.Utilisateur;
import com.example.demo.security.JwtUtils;
import com.example.demo.security.UserDetailsDemo;
import com.example.demo.security.UserDetailsServiceDemo;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.*;

@CrossOrigin
@RestController
public class UtilisateurController {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceDemo userDetailsServiceDemo;

    @Autowired
    private PasswordEncoder encoder;

    private UtilisateurDao utilisateurDao;

    @Autowired
    public UtilisateurController(UtilisateurDao utilisateurDao) {
        this.utilisateurDao = utilisateurDao;
    }




    @PostMapping("/initialisation")
    public String initialisation() {

        for(int i = 1; i <= 100; i ++) {
            Utilisateur utilisateur = new Utilisateur();
            utilisateur.setNom("Utilisateur fictif " + i);
            utilisateur.setMotDePasse("root");
            utilisateurDao.save(utilisateur);
        }

        return "utilisateurs créé";
    }

    @PostMapping("/inscription")
    public ResponseEntity<String> inscription(@RequestBody Utilisateur utilisateur) {

        utilisateur.setMotDePasse(encoder.encode(utilisateur.getMotDePasse()));

        utilisateurDao.save(utilisateur);

        return ResponseEntity.ok().build();
    }


    @PostMapping("/connexion")
    public Map<String,String> connexion(@RequestBody Utilisateur utilisateur) throws Exception {

        try {

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            utilisateur.getEmail(),
                            utilisateur.getMotDePasse()
                    )
            );
        } catch (BadCredentialsException e) {

            Map<String,String> retour = new HashMap<>();
            retour.put("erreur", "Mauvais login / mot de passe");
            return retour;

        }

        UserDetailsDemo userDetails = userDetailsServiceDemo
                .loadUserByUsername(utilisateur.getEmail());

        Map<String,String> retour = new HashMap<>();
        retour.put("token", jwtUtils.generateToken(userDetails));

        return retour;

    }

    @GetMapping("/liste-utilisateur")
    public ResponseEntity<List<Utilisateur>> listeUtilisateur () {

        return ResponseEntity.ok(utilisateurDao.findAll());

    }

    @GetMapping("/utilisateur-par-nom/{nom}")
    public Utilisateur utilisateurParNom(@PathVariable String nom) {

        return this.utilisateurDao.findByNom(nom).orElse(null);
    }

    @GetMapping("/utilisateur/{id}")
    public ResponseEntity<Utilisateur> utilisateur(@PathVariable Integer id) {

        Optional<Utilisateur> utilisateur = this.utilisateurDao.findById(id);

        if(utilisateur.isPresent()) {
            return ResponseEntity.ok(utilisateur.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/utilisateur")
    public ResponseEntity<String> createUtilisateur(
            @RequestBody Utilisateur utilisateur) {

        if(utilisateur.getId() != null) {

            Optional<Utilisateur> utilisateurBaseDeDonnee =
                    utilisateurDao.findById(utilisateur.getId());

            if(utilisateurBaseDeDonnee.isPresent()) {

                utilisateurBaseDeDonnee.get().setNom(utilisateur.getNom());
                utilisateurBaseDeDonnee.get().setPrenom(utilisateur.getPrenom());
                utilisateurBaseDeDonnee.get().setEmail(utilisateur.getEmail());

                this.utilisateurDao.save(utilisateurBaseDeDonnee.get());
                return ResponseEntity.ok("Utilisateur modifié");

            } else {
                return ResponseEntity
                        .status(HttpStatus.NOT_MODIFIED)
                        .build();
            }
        } else {
            this.utilisateurDao.save(utilisateur);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
    }

    @DeleteMapping("/utilisateur/{id}")
    public ResponseEntity<Utilisateur> deleteUtilisateur(@PathVariable int id){

        Optional<Utilisateur> utilisateurAsupprimer = utilisateurDao.findById(id);

        if(utilisateurAsupprimer.isPresent()){
            utilisateurDao.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/deconnexion")
    public ResponseEntity<String> deconnexion(@RequestHeader("Authorization") String jwt){

        String token = jwt.substring(7);
        int idUtilisateurConnecte = (int)jwtUtils.getTokenBody(token).get("id");

        Optional<Utilisateur> utilisateurOptional =
                utilisateurDao.findById(idUtilisateurConnecte);

        if(utilisateurOptional.isPresent()) {
            utilisateurOptional.get().setNumeroToken(
                    utilisateurOptional.get().getNumeroToken() + 1);

            utilisateurDao.save(utilisateurOptional.get());

            return ResponseEntity.ok().build();
        }

        return ResponseEntity.noContent().build();

    }

}
