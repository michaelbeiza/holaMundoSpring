package com.example.demo.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.demo.domain.Pais;
import com.example.demo.domain.Persona;
import com.example.demo.exception.DangerException;
import com.example.demo.helper.PRG;
import com.example.demo.repository.AficionRepository;
import com.example.demo.repository.PaisRepository;
import com.example.demo.repository.PersonaRepository;




@Controller
@RequestMapping("/persona")
public class PersonaController {
    @Autowired
    private PersonaRepository per;
    @Autowired
    private PaisRepository par;
    @Autowired
    private AficionRepository afr;



    @GetMapping("c")
    public String datosGet(ModelMap m) {
        m.put("paises", par.findAll());
        m.put("aficiones", afr.findAll());
        m.put("view", "persona/cGet");
        return "_t/frame";
    }

    @PostMapping("c")
    public String crearPost(
        @RequestParam(value ="nombre",required=false)
        String nombre,
        @RequestParam(required = true) String loginName,
        @RequestParam(required = true) String pwd,
        @RequestParam("idPaisNace")Long idPaisNace,
        @RequestParam("idPaisVive")Long idPaisVive,
        @RequestParam(value = "idAficionGu", required = false)Collection<Long> idGusto,
        @RequestParam(value ="idAficionOd", required = false)Collection<Long> idOdio,
        ModelMap m
        //Guarda los datos de la nueva persona
    ){
        
        Persona persona = Persona.builder()
        .nombre(nombre)
        .pwd(pwd)
        .loginName(loginName)
        .build();

        Pais nace = par.getReferenceById(idPaisNace);
        Pais vive = par.getReferenceById(idPaisVive);

        persona.setPaisNacimiento(nace);
        persona.setPaisResidencia(vive);

        nace.getNacidos().add(persona);
        vive.getResidentes().add(persona);

        if (idGusto != null) {
            for (Long id : idGusto) {
                persona.getGustos().add(afr.getReferenceById(id));
            }
        }
        if (idOdio != null) {
        for (Long id : idOdio) {
            persona.getOdios().add(afr.getReferenceById(id));
        }
        }
        per.save(persona);
        return "redirect:/persona/r";
    }

    @GetMapping("r")
    public String r(ModelMap m) {
        m.put("personas" , per.findAll());
        m.put("view", "persona/r");
        return "_t/frame";
    }

    @GetMapping("u")
    public String uGet(@RequestParam("idPersona") Long idPersona, ModelMap m) {
        m.put("persona" , per.getReferenceById(idPersona));
        m.put("view", "persona/uGet");
        return "_t/frame";
    }

    @PostMapping("u")
    public String uPost(
        @RequestParam("nombre") String nombre,
        @RequestParam("idPersona") Long idPersona,
        ModelMap m) throws DangerException {
        Persona personaModificar = per.getReferenceById(idPersona);
        personaModificar.setNombre(nombre);
        try {
            per.save(personaModificar);
        } catch (Exception e) {
            PRG.error("La persona de nombre" + nombre + "ya existe", "/persona/r");
        }
        return "redirect:/persona/r";
    }
    
}
