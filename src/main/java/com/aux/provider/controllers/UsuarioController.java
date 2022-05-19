package com.aux.provider.controllers;

import com.aux.provider.models.PerfilModel;
import com.aux.provider.models.ProveedorModel;
import com.aux.provider.models.UsuarioModel;
import com.aux.provider.services.PerfilService;
import com.aux.provider.services.ProveedorService;
import com.aux.provider.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;
    ProveedorService proveedorService;
    PerfilService perfilService;

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

 @GetMapping( path = "/validar")
    public String validarUsuario(@RequestParam("email") String email, @RequestParam("clave") String clave){
     return this.usuarioService.validarUsuario(email,clave);

    }

    @PutMapping("/{id}")
    public UsuarioModel actualizarUsuario(@RequestBody UsuarioModel usuario,@PathVariable("id") Long id){
        return this.usuarioService.actualizarUsuario(usuario, id);
    }

    @DeleteMapping(path = "/{id}")
    public String eliminarPorId(@PathVariable("id") Long id){
        boolean msg = this.usuarioService.eliminarUsuario(id);
        if(msg){
            return "Se elimino el usuario con id: " + id;
        }else {
            return "No se pudo eliminar el usuario con id: " + id;
        }
    }
}
