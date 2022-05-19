package org.example;

public class AssembleurTest {

    Bateau bateau;
    Voiture voiture;
    MoteurMock moteur;

    public AssembleurTest() {
        moteur = new MoteurMock();
        voiture = new Voiture(moteur);
        bateau = new Bateau(moteur);
    }

    public Voiture getVoiture() {
        return voiture;
    }

    public MoteurMock getMoteur() {
        return moteur;
    }

    public Bateau getBateau() {
        return bateau;
    }
}
