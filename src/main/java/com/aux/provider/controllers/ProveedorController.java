package com.aux.provider.controllers;

import com.aux.provider.models.PerfilModel;
import com.aux.provider.models.ProveedorModel;
import com.aux.provider.models.UsuarioModel;
import com.aux.provider.services.ProveedorService;
import com.aux.provider.services.exceptions.NoEncontradoException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProveedorController {
    private final ProveedorService proveedorService;

    @GetMapping("/proveedores")
    public ResponseEntity<List<ProveedorModel>> getProveedores(){
        return  ResponseEntity.ok().body(proveedorService.getProveedores());
    }

    @PostMapping("/proveedor/save")
    public ResponseEntity<ProveedorModel> saveProveedor(@RequestBody ProveedorModel proveedorModel){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/proveedor/save").toUriString());
        return  ResponseEntity.created(uri).body(proveedorService.saveProveedor(proveedorModel));
    }

    @PostMapping("/perfil/save")
    public ResponseEntity<PerfilModel> savePerfil(@RequestBody PerfilModel perfilModel){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/perfil/save").toUriString());
        return  ResponseEntity.created(uri).body(proveedorService.savePerfil(perfilModel));
    }

    @PostMapping("/usuario/save")
    public ResponseEntity<UsuarioModel> saveUsuario(@RequestBody UsuarioModel usuarioModel){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/usuario/save").toUriString());
        return  ResponseEntity.created(uri).body(proveedorService.saveUsuario(usuarioModel));
    }

    @PostMapping("/perfil/setToProveedor")
    public ResponseEntity<?> setPerfilToPro(@RequestBody setPerfilForm form) throws NoEncontradoException {
        proveedorService.setPerfilToProveedor(form.getId_perfil(), form.getId_proveedor());
        return  ResponseEntity.ok().build();
    }

    @PostMapping("/usuario/setToProveedor")
    public ResponseEntity<?> setUsuarioToPro(@RequestBody setUsuarioForm form) throws NoEncontradoException {
        proveedorService.setUsuarioToProveedor(form.getEmail_usuario(), form.getId_proveedor());
        return  ResponseEntity.ok().build();
    }


}
    @Data
    class setUsuarioForm{
        private long id_proveedor;
        private String email_usuario;
    }

    @Data
    class setPerfilForm{
        private long id_proveedor;
        private long id_perfil;
    }