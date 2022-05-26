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
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RequestMapping("/api")
public class ProveedorController {
    //Invocamos los servicios(Acciones permitidas) que le corresponden al controlador
    private final ProveedorService proveedorService;

    //Peticion GET para obtener la informacion de todos los proveedores
    @GetMapping("/proveedores")
    public ResponseEntity<List<ProveedorModel>> getProveedores(){
        return  ResponseEntity.ok().body(proveedorService.getProveedores());
    }

    //Peticion GET para obtener la informacion de un proveedor por medio del ID

    @GetMapping("/proveedor/{id}")
    public ResponseEntity<ProveedorModel> getProveedor(@PathVariable("id") Long id) throws NoEncontradoException {
        return  ResponseEntity.ok().body(proveedorService.getProveedor(id));
    }

    //Peticion GET para obtener la informacion de un proveedor por medio del Email
    @GetMapping("/proveedor/email/{email}")
    public ResponseEntity<ProveedorModel> getProveedorByEmail(@PathVariable("email") String email) throws NoEncontradoException {
        return  ResponseEntity.ok().body(proveedorService.getProveedorbyEmail(email));
    }

    //Peticion POST para guardar la informacion del proveedor a la base de datos
    @PostMapping("/proveedor/save")
    public ResponseEntity<ProveedorModel> saveProveedor(@RequestBody ProveedorModel proveedorModel){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/proveedor/save").toUriString());
        return  ResponseEntity.created(uri).body(proveedorService.saveProveedor(proveedorModel));
    }



}
