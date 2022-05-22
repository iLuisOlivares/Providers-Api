package com.aux.provider.controllers;

import com.aux.provider.models.PerfilModel;
import com.aux.provider.services.PerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;

@RestController
@RequestMapping("/api")
public class PerfilController {
    @Autowired
    PerfilService perfilService;

    @PostMapping("/perfil/save")
    public ResponseEntity<PerfilModel> savePerfil(@RequestBody PerfilModel perfilModel){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/perfil/save").toUriString());
        return  ResponseEntity.created(uri).body(perfilService.savePerfil(perfilModel));
    }
}
