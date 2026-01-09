package com.adrian.firststep.domain.model.armas;

import org.springframework.stereotype.Component;

import com.adrian.firststep.domain.model.IArma;

@Component("canonDePlasma")
public class CanonDePlasma implements IArma {

    @Override
    public void atacar() {
        System.out.println("BOOM! atacado por un Cañon de Plasma");
    }
    
}