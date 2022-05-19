package com.example.demo.model;

import com.example.demo.view.VueMarque;
import com.example.demo.view.VueMateriel;
import com.example.demo.view.VueReservation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)

public class Materiel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({VueMarque.class, VueMateriel.class, VueReservation.class })
    private Integer id;

    @Column(unique = true)
    @JsonView({VueMarque.class, VueMateriel.class, VueReservation.class})
    private String code;

    @JsonView({VueMarque.class, VueMateriel.class, VueReservation.class})
    private String nom;

    @ManyToOne
    @JsonView(VueMateriel.class)
    private Marque marque;

    @ManyToMany
    @JsonView(VueMateriel.class)
    @JoinTable(
            name = "materiel_specificite",
            joinColumns = @JoinColumn(name = "materiel_id"),
            inverseJoinColumns = @JoinColumn(name = "specificite_id"))
    private List<Specificite> listeSpecificite = new ArrayList<>();


}
