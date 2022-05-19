package com.aux.provider.controllers;

import com.aux.provider.models.ServicioModel;
import com.aux.provider.models.UsuarioModel;
import com.aux.provider.services.ServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/servicios")
public class ServicioController {
    @Autowired
    ServicioService servicioService;

    @GetMapping()
    public ArrayList<ServicioModel> obtenerServicios(){return servicioService.obtenerServicios();}

    @PostMapping()
    public String guardarServicio(@RequestBody ServicioModel servicio){
        boolean msg = this.servicioService.guardarServicio(servicio);
        if(msg){
            return "Se guardo el usuario con correctamente";
        }else {
            return "Error: No se pudo guardar el usuario";
        }
    }
}
