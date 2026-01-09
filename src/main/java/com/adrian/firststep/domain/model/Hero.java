package com.adrian.firststep.domain.model;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Hero {
    private String name;
    private int level;
    private List<Skill> skills;
    private IArma arma;

    public Hero(List<Skill> skills, IArma arma) {
        this.skills = skills;
        this.arma = arma;
    }

    public Hero(String nombre, int nivel, List<Skill> skills) {
        this.name = nombre;
        this.level = nivel;
        arma = null;
    }

    public void equipar(IArma arma) {
        this.arma = arma;
    }

    public void atacar() {
        arma.atacar();
    }
    
}