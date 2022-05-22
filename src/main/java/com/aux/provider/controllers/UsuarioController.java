package com.aux.provider.controllers;

import com.aux.provider.models.PerfilModel;
import com.aux.provider.models.ProveedorModel;
import com.aux.provider.models.UsuarioModel;
import com.aux.provider.services.PerfilService;
import com.aux.provider.services.ProveedorService;
import com.aux.provider.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;
    ProveedorService proveedorService;
    PerfilService perfilService;

    @PostMapping("/usuario/save")
    public ResponseEntity<UsuarioModel> saveUsuario(@RequestBody UsuarioModel usuarioModel){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/usuario/save").toUriString());
        return  ResponseEntity.created(uri).body(usuarioService.saveUsuario(usuarioModel));
    }

    @GetMapping()
    public ArrayList<UsuarioModel> obtenerUsuarios(){
        return usuarioService.obtenerUsuarios();
    }

    @PostMapping()
    public String RegistrarUsuario(@RequestBody UsuarioModel usuario){

        boolean msg = this.usuarioService.guardarUsuario(usuario);
        if(msg ){
            return "Se guardo el usuario con correctamente";
        }else {
            return "Error: No se pudo guardar el usuario";
        }
    }

    @GetMapping( path = "/{id}")
    public Optional<UsuarioModel> obtenerUsuarioPorId(@PathVariable("id") Long id){
        return this.usuarioService.obtenerPorId(id);
    }

    /*@GetMapping( path = "/query")
    public ArrayList<UsuarioModel> obtenerUsuarioPorEmail(@RequestParam("email") String email){
        return this.usuarioService.obtenerPorEmail(email);
    }*/

    @PutMapping("/{id}")
    public UsuarioModel actualizarUsuario(@RequestBody UsuarioModel usuario,@PathVariable("id") Long id){
        return this.usuarioService.actualizarUsuario(usuario, id);
    }

}
