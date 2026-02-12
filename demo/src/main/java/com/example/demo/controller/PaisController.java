package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.domain.Pais;
import com.example.demo.exception.DangerException;
import com.example.demo.helper.PRG;
import com.example.demo.repository.PaisRepository;


@Controller
@RequestMapping("/pais")
public class PaisController {
    @Autowired
    private PaisRepository pr;


    @GetMapping("c")
    public String datosGet( ModelMap m){
    m.put("view", "pais/cGet");
    return "_t/frame";
    }

    @PostMapping("c")
    public String crearPost(
        @RequestParam("nombre") String nombre,
        ModelMap m
        
    )throws DangerException {
    
        if (nombre == null || nombre.trim().isEmpty()) {
            PRG.error("El nombre del país no puede estar vacío", "/pais/c");
        }
    try {
        pr.save(new Pais(nombre));
    } catch (Exception e) {
        
        PRG.error("El país '" + nombre + "' ya existe en nuestra base de datos.", "/pais/c");
    }
    return "redirect:/pais/r";
}

    @GetMapping("r")
    public String r(ModelMap m) {
        m.put("paises" , pr.findAll());
        m.put("view", "pais/r");
        return "_t/frame";
    }

    @GetMapping("u")
    public String uGet(@RequestParam("idPais") Long idPais, ModelMap m) {
        m.put("pais" , pr.getReferenceById(idPais));
        m.put("view", "pais/uGet");
        return "_t/frame";
    }

    @PostMapping("u")
    public String uPost(
        @RequestParam("nombre") String nombre,
        @RequestParam("idPais") Long idPais,
        ModelMap m) throws DangerException {
        Pais paisModificar = pr.getReferenceById(idPais);
        paisModificar.setNombre(nombre);
        try {
            pr.save(paisModificar);
        } catch (Exception e) {
            PRG.error("El pais de nombre" + nombre + "ya existe", "/pais/r");
        }
        return "redirect:/pais/r";
    }
}
