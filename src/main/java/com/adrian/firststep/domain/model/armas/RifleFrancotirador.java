package com.adrian.firststep.domain.model.armas;

import org.springframework.stereotype.Component;

import com.adrian.firststep.domain.model.IArma;

@Component("rifleFrancotirador")
public class RifleFrancotirador implements IArma {

    @Override
    public void atacar() {
        System.out.println("HEADSHOT! manco!");
    }
    
}