package org.example;

public class Assembleur {

    Bateau bateau;
    Voiture voiture;
    Moteur moteur;

    public Assembleur() {
        moteur = new Moteur();
        voiture = new Voiture(moteur);
        bateau = new Bateau(moteur);
    }

    public Voiture getVoiture() {
        return voiture;
    }

    public Moteur getMoteur() {
        return moteur;
    }

    public Bateau getBateau() {
        return bateau;
    }
}
