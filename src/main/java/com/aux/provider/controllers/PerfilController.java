package com.aux.provider.controllers;

import com.aux.provider.models.PerfilModel;
import com.aux.provider.services.PerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/perfiles")
public class PerfilController {
    @Autowired
    PerfilService perfilService;

    @GetMapping()
    public ArrayList<PerfilModel> obtenerPerfiles(){return perfilService.obtenerServicios();}

    @PostMapping()
    public String guardarPerfil(@RequestBody PerfilModel perfil){
        boolean msg = this.perfilService.guardarPerfil(perfil);
        if(msg){
            return "Se guardo el perfil correctamente";
        }else {
            return "Error: No se pudo guardar el perfil";
        }
    }
}
