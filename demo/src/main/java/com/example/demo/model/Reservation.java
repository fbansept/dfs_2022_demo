package com.example.demo.model;

import com.example.demo.view.VueReservation;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(CleReservation.class)

public class Reservation {

    @Id
    private Integer emprunteurId;

    @Id
    private Integer materielId;

    @Id
    @JsonView(VueReservation.class)
    private Date date;

    @ManyToOne
    @MapsId("emprunteur_id")
    @JsonView(VueReservation.class)
    private Utilisateur emprunteur;

    @ManyToOne
    @MapsId("materiel_id")
    @JsonView(VueReservation.class)
    private Materiel materiel;

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

    public Utilisateur getEmprunteur() {
        return emprunteur;
    }

    public void setEmprunteur(Utilisateur emprunteur) {
        this.emprunteur = emprunteur;
    }
}
