package com.adrian.firststep.domain.model.armas;

import org.springframework.stereotype.Component;

import com.adrian.firststep.domain.model.IArma;

@Component("machete")
public class Machete implements IArma {
    
    @Override
    public void atacar() {
        System.out.println("El filo de la hoja corta el aire antes de impactar sobre el enemigo y prococarle un gran corte");
    }
}
