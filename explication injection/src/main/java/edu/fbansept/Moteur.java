package edu.fbansept;

public class Moteur implements IMoteur {

    private Integer nombreCylindre;

    @Override
    public int getNombreCylindre() {
        return nombreCylindre;
    }

    @Override
    public void setNombreCylindre(int nombreCylindre) {
        this.nombreCylindre = nombreCylindre;
    }
}
