package com.example.demo.security;

import com.example.demo.model.Role;
import com.example.demo.model.Utilisateur;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class UserDetailsDemo implements UserDetails {

    private Utilisateur utilisateur;

    public UserDetailsDemo(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        ArrayList<SimpleGrantedAuthority> listeAuthority = new ArrayList<>();

        //----GESTION DES DROITS PAR HERITAGE----
        /*Optional<Admin> admin = adminDao.findById(utilisateur.getId());

        if(admin.isPresent()) {
            listeAuthority.add(new SimpleGrantedAuthority("ROLE_ADMIN));
        }*/

        //----GESTION DES DROITS PAR BOOLEEN----
        /*if(this.utilisateur.isAdmin()) {
            listeAuthority.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }*/

        //----GESTION DES DROITS PAR TABLE ROLE (MANY TO MANY)----
        for(Role role : this.utilisateur.getListeRole()){
            listeAuthority.add(new SimpleGrantedAuthority(role.getNom()));
        }

        //----GESTION DES DROITS PAR TABLE ROLE (MANY TO ONE)----
        //listeAuthority.add(new SimpleGrantedAuthority(this.utilisateur.getRole().getNom()));

        //----GESTION DES DROITS PAR TABLE ROLE AVEC DROITS ASSOCIES ----
        /*for(Role ro : this.utilisateur.getListeRole()){
            for(Droit droit : role.getListeDroit()){
                listeAuthority.add(new SimpleGrantedAuthority(droit.getNom()));
            }
        }*/

        return listeAuthority;

    }

    @Override
    public String getPassword() {
        return utilisateur.getMotDePasse();
    }

    @Override
    public String getUsername() {
        return utilisateur.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
