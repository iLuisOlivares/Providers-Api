package com.aux.provider.controllers;

import com.aux.provider.models.PerfilModel;
import com.aux.provider.models.UsuarioModel;
import com.aux.provider.services.PerfilService;
import com.aux.provider.services.exceptions.NoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;

@RestController
@RequestMapping("/api")
public class PerfilController {
    // Invocamos los servicios(Acciones permitidas) que le corresponden al controlador
    @Autowired
    PerfilService perfilService;

    // Peticion GET para buscar un unico perfil
    @GetMapping("/perfil/{id}")
    public ResponseEntity<PerfilModel> getPerfil(@PathVariable("id") long id) throws NoEncontradoException {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/perfil/{id}").toUriString());
        return  ResponseEntity.created(uri).body(perfilService.getPerfilByProveedor(id));
    }

    // Peticion POST para guardar un perfil a la base de datos
    @PostMapping("/perfil/save")
    public ResponseEntity<PerfilModel> savePerfil(@RequestBody PerfilModel perfilModel){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/perfil/save").toUriString());
        return  ResponseEntity.created(uri).body(perfilService.savePerfil(perfilModel));
    }

    // Peticion PUT para Actualizar un perfil
    @PutMapping("/perfil/update/{id}")
    public ResponseEntity<PerfilModel> updatePerfil(@RequestBody PerfilModel perfilModel, @PathVariable("id") Long id) throws NoEncontradoException {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/perfil/update").toUriString());
        return  ResponseEntity.created(uri).body(perfilService.updatePerfil(perfilModel,id));
    }

}
