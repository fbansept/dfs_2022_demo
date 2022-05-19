package com.example.demo.security;

import com.example.demo.dao.UtilisateurDao;
import com.example.demo.model.Utilisateur;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserDetailsServiceDemo implements UserDetailsService {

    private UtilisateurDao utilisateurDao;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    UserDetailsServiceDemo(UtilisateurDao utilisateurDao){
        this.utilisateurDao = utilisateurDao;
    }

    @Override
    public UserDetailsDemo loadUserByUsername(String email) throws UsernameNotFoundException {

        Utilisateur utilisateur = utilisateurDao
                .findByEmailWithRoles(email)
                .orElseThrow(() -> new UsernameNotFoundException("Mauvais pseudo / mot de passe"));

        UserDetailsDemo userDetailsDemo = new UserDetailsDemo(utilisateur);

        return userDetailsDemo;
    }
}
