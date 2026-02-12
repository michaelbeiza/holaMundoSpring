package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.domain.Aficion;
import com.example.demo.exception.DangerException;
import com.example.demo.helper.PRG;
import com.example.demo.repository.AficionRepository;


@Controller
@RequestMapping("/aficion")
public class AficionController {

    @Autowired
    private AficionRepository afr;

    @GetMapping("c")
    public String datosGet(ModelMap m) {
        m.put("view", "aficion/cGet");
        return "_t/frame";
    }

    @PostMapping("c")
    public String crearPost(
        @RequestParam(value = "nombre", required = false) String nombre
    ) throws DangerException {
        try {
            if (nombre == null || nombre.trim().isEmpty()) {
                PRG.error("El nombre de la afición no puede estar vacío", "/aficion/c");
            }
            afr.saveAndFlush(new Aficion(nombre));
        } catch (Exception e) {
            // Esto saltará si el nombre está duplicado por el @Column(unique = true)
            PRG.error("La afición '" + nombre + "' ya existe.", "/aficion/c");
        }
        return "redirect:/aficion/r";
    }

    @GetMapping("r")
    public String r(ModelMap m) {
        m.put("aficiones", afr.findAll());
        m.put("view", "aficion/r");
        return "_t/frame";
    }

    @GetMapping("u")
    public String uGet(@RequestParam("idAficion") Long idAficion, ModelMap m) {
        m.put("aficion", afr.getReferenceById(idAficion));
        m.put("view", "aficion/uGet");
        return "_t/frame";
    }

    @PostMapping("u")
    public String uPost(
        @RequestParam("nombre") String nombre,
        @RequestParam("idAficion") Long idAficion) throws DangerException {
        Aficion aficionModificar = afr.getReferenceById(idAficion);
        aficionModificar.setNombre(nombre);
        try {
            afr.save(aficionModificar);
        } catch (Exception e) {
            PRG.error("La afción de nombre" + nombre + "ya existe.", "/aficion/r");
        }

        return "redirect:/aficion/r";
    }
    
}