package edu.fbansept;

public class Main {

    public static void main(String[] args) {

        IMoteur moteur1 = new Moteur();

        Voiture voiture = new Voiture(moteur1);

        voiture.demarrer();

    }
}
