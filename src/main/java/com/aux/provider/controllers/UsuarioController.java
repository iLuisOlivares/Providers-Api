package com.aux.provider.controllers;

import com.aux.provider.models.PerfilModel;
import com.aux.provider.models.ProveedorModel;
import com.aux.provider.models.UsuarioModel;
import com.aux.provider.services.PerfilService;
import com.aux.provider.services.ProveedorService;
import com.aux.provider.services.UsuarioService;
import com.aux.provider.services.exceptions.NoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UsuarioController {

    // Invocamos los servicios(Acciones permitidas) que le corresponden al controlador
    @Autowired
    UsuarioService usuarioService;

    // Peticion GET para obtener una lista de todos los usuarios y su  informacion
    @GetMapping()
    public ArrayList<UsuarioModel> obtenerUsuarios(){
        return usuarioService.obtenerUsuarios();
    }

    // Peticion GET para obtener usuarios a partir de su id
    @GetMapping( path = "/usuario/{id}")
    public Optional<UsuarioModel> obtenerUsuarioPorId(@PathVariable("id") Long id){
        return usuarioService.obtenerPorId(id);
    }

    // Peticion POST para guardar usuarios a la base de datos
    @PostMapping("/usuario/save")
    public ResponseEntity<UsuarioModel> saveUsuario(@RequestBody UsuarioModel usuarioModel){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/usuario/save").toUriString());
        return  ResponseEntity.created(uri).body(usuarioService.saveUsuario(usuarioModel));
    }


}
