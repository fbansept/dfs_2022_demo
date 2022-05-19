package com.example.demo.controller;

import com.example.demo.dao.UtilisateurDao;
import com.example.demo.model.Marque;
import com.example.demo.model.Role;
import com.example.demo.model.Utilisateur;
import com.example.demo.security.JwtUtils;
import com.example.demo.security.UserDetailsDemo;
import com.example.demo.security.UserDetailsServiceDemo;
import org.springframework.beans.factory.annotation.Autowired;
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
            throw new Exception(e);
        }

        UserDetailsDemo userDetails = userDetailsServiceDemo
                .loadUserByUsername(utilisateur.getEmail());

        Map<String,String> retour = new HashMap<>();
        retour.put("token", jwtUtils.generateToken(userDetails));

        return retour;

    }

    @GetMapping("/liste-utilisateur")
    public List<Utilisateur> listeUtilisateur () {

        List<Map<String, Object>> requete = utilisateurDao.selectUtilisateur();

        List<Utilisateur> listeUtilisateur = new ArrayList<>();

        for( Map<String, Object> ligneResultat : requete){

            Utilisateur utilisateur = new Utilisateur();

            int id = (int)ligneResultat.get("id");
            String nom = (String)ligneResultat.get("nom");
            String prenom = (String)ligneResultat.get("prenom");
            String moDePasse = (String)ligneResultat.get("mot_de_passe");

            utilisateur.setId(id);
            utilisateur.setNom(nom);
            utilisateur.setPrenom(prenom);
            utilisateur.setMotDePasse(moDePasse);

            listeUtilisateur.add(utilisateur);
        }

        return utilisateurDao.findAll();
    }

    @GetMapping("/utilisateur-par-nom/{nom}")
    public Utilisateur utilisateurParNom(@PathVariable String nom) {

        return this.utilisateurDao.findByNom(nom).orElse(null);
    }

    @GetMapping("/utilisateur/{id}")
    public Utilisateur utilisateur(@PathVariable Integer id) {

        return this.utilisateurDao.findById(id).orElse(null);

    }

    @PostMapping("/utilisateur")
    public String createUtilisateur(@RequestBody Utilisateur utilisateur) {

        this.utilisateurDao.save(utilisateur);

        return "ok";
    }

    @DeleteMapping("/utilisateur/{id}")
    public ResponseEntity<Utilisateur> deleteUtilisateur(@PathVariable int id){

        Optional<Utilisateur> utilisateurAsupprimer = utilisateurDao.findById(id);

        if(utilisateurAsupprimer.isPresent()){
            utilisateurDao.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.noContent().build();
        }
    }

}
