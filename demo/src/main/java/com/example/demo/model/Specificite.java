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
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Specificite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(VueMateriel.class)
    private Integer id;

    @JsonView(VueMateriel.class)
    private String nom;

}
