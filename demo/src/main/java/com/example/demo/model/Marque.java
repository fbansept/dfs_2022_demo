package com.example.demo.model;

import com.example.demo.view.VueMarque;
import com.example.demo.view.VueMateriel;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Marque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({VueMarque.class, VueMateriel.class})
    private Integer id;

    @JsonView({VueMarque.class, VueMateriel.class})
    private String nom;

    @OneToMany(mappedBy = "marque")
    @JsonView(VueMarque.class)
    private List<Materiel> listeMateriel = new ArrayList<>();

    public List<Materiel> getListeMateriel() {
        return new ArrayList<>();
    }
}
