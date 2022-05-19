package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Date;

@Embeddable
public class CleReservation implements Serializable {

    @Column(name = "emprunteur_id")
    private Integer emprunteurId;

    @Column(name = "materiel_id")
    private Integer materielId;

    @Column(name = "date")
    private Date date;

    public Integer getEmprunteurId() {
        return emprunteurId;
    }

    public void setEmprunteurId(Integer emprunteurId) {
        this.emprunteurId = emprunteurId;
    }

    public Integer getMaterielId() {
        return materielId;
    }

    public void setMaterielId(Integer materielId) {
        this.materielId = materielId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
