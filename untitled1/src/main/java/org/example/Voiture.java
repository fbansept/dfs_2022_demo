package org.example;

public class Voiture {

    private IMoteur IMoteur;

    public Voiture(IMoteur IMoteur) {
        this.IMoteur = IMoteur;
    }

    public String demarrer() {

        return "DEMARRAGE avec " +
                IMoteur.getNombreCylindre() + " cylindres";
    }

}
