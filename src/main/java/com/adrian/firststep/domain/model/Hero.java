package com.adrian.firststep.domain.model;

import java.util.List;
import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Hero {
    private String nombre;
    private int nivel;
    private List<Skill> skills;
    private IArma arma;

    public Hero(List<Skill> skills) {
        skills = new ArrayList<>();
        arma = null;
    }

    public Hero(String nombre, int nivel) {
        this.nombre = nombre;
        this.nivel = nivel;
    }

    public void equipar(IArma arma) {
        
    }

}
