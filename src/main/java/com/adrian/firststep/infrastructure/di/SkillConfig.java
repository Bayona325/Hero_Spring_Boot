package com.adrian.firststep.infrastructure.di;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.adrian.firststep.domain.model.Hero;
import com.adrian.firststep.domain.model.IArma;
import com.adrian.firststep.domain.model.Skill;

@Configuration
public class SkillConfig {

    @Bean
    public List<Skill> stackSkillDefault() {
        Skill skill = new Skill();
        skill.setDamage(10);
        skill.setName("Golpe Suave");
        return List.of(skill);
    }

    @Bean
    public Hero heroDefault(List<Skill> skills, @Qualifier("canonDePlasma") IArma arma) {
        Hero hero = new Hero(skills, arma);
        hero.setName("Son ponscio");
        return hero;
    }

    /*
    @Bean
    public Hero heroDamage(List<Skill> skills, @Qualifier("rifleFrancotirador") IArma arma) {
        Hero hero = new Hero(skills, arma);
        hero.setName("Son ponscio");
        return hero;
    }
    */
}