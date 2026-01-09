package com.adrian.firststep.infrastructure.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.adrian.firststep.domain.model.Hero;
import com.adrian.firststep.infrastructure.mapper.HeroRequest;
import com.adrian.firststep.infrastructure.mapper.HeroResponse;

import jakarta.websocket.server.PathParam;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "api/v1", produces = {MediaType.APPLICATION_JSON_VALUE})
public class HelloWorldController {
    
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

        //procesar - Bussines

        //la respuesta

        return new HeroResponse(, LocalDateTime.now());
    }
    
    @PostMapping(path = "/actualizar", consumes = {"application/json-patch+json"})
    public Hero actualizarEntidad(@RequestBody(required = false) Hero hero) {
        
        return new Hero(hero.getNombre(), hero.getNivel());
    }

    @PutMapping(path = "/hero/{id}/actualizar", consumes = {"application/json-patch+json"})
    public Map<String, Object> actualizarPorId(@RequestBody(required = false) Hero hero, @PathVariable(name = "id") String heroId) {
        return Map.of("hero", new Hero(hero.getNombre(), hero.getNivel()), "id", heroId);
    }

}
