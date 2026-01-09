package com.adrian.firststep.infrastructure.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.adrian.firststep.domain.model.Hero;
import com.adrian.firststep.infrastructure.mapper.HeroRequest;
import com.adrian.firststep.infrastructure.mapper.HeroResponse;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(path = "api/v1", produces = {MediaType.APPLICATION_JSON_VALUE})
public class HelloWorldController {

    private Hero hero;

    public HelloWorldController(Hero hero) {
        this.hero = hero;
    }
    
    @GetMapping("/greetings")
    public String holaMundo(@RequestParam(name = "name", defaultValue = "Ponscia") String nombre) {
        return "Hola " +nombre+" Camper!";
    }
    

    @GetMapping("/json")
    public Map<String, String> jsonExample(@RequestParam String name) {
        Map<String, String> response = new HashMap<>();
        response.put("greeting", "Hellou everynian!");
        response.put("user", name);
        return response;
    }

    @PostMapping(path = "/create", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public HeroResponse crearEntidad(@RequestBody HeroRequest request) {
        
        //alias
        if(request.alias().isEmpty()) {
            throw new IllegalArgumentException("Los campos no pueden ser nulos");
        }

        //procesar - Bussines
        /* Skill skill = new Skill();
        skill.setDamage(10);
        skill.setName("Inicial");
        List<Skill> skills = List.of(skill, skill);
        Hero newHero = new Hero(skills);
        newHero.setName(request.alias());
        newHero.setLevel(1); */
        //la respuesta
        hero.atacar();
        return new HeroResponse(hero.getName(), hero.getLevel(), LocalDateTime.now());
    }

    

    
    
    
}