package com.adrian.firststep.domain.model.armas;

import org.springframework.stereotype.Component;

import com.adrian.firststep.domain.model.IArma;

@Component("pistolaSimple")
public class PistolaSimple implements IArma {
    
    @Override
    public void atacar() {
        System.out.println("BAMG! Disparo al enemigo");
    }
}
