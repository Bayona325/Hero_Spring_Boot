package com.adrian.firststep.domain.model;

public class CanonDePlasma implements IArma {
    
    @Override
    public void atacar() {
        System.out.print("BOOM! Atacado por un Cañon de Plasma");
    }
}
