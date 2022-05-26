package com.aux.provider.controllers;

import com.aux.provider.models.ServicioModel;
import com.aux.provider.services.ServicioService;
import com.aux.provider.services.exceptions.NoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ServicioController {
    // Invocamos los servicios(Acciones permitidas) que le corresponden al controlador
    @Autowired
    ServicioService servicioService;

    // Peticion GET para obtener una lista de todos los servicios y su  informacion
    @GetMapping("/servicios")
    public ArrayList<ServicioModel> obtenerServicios(){return servicioService.getServicios();}

    // Peticion GET para obtener una lista de servicios por el id del proveedor
    @GetMapping("/servicio/{id}")
    public List<ServicioModel> getServicio(@PathVariable("id") Long id){
        return servicioService.getServiciosProv(id);
    }

    // Peticion POST para guardar servicios de un proveedor a la base de datos
    @PostMapping("/servicios/update/{id}")
    public ServicioModel saveServicio(@RequestBody ServicioModel servicio, @PathVariable("id") Long id) throws NoEncontradoException {
        return servicioService.updateServicio(servicio, id);

    }
    // Peticion PUT para actualizar un servicio por medio de ID
    @PutMapping("/servicios/update/{id}")
    public ServicioModel updateServicio(@RequestBody ServicioModel servicio, @PathVariable("id") Long id) throws NoEncontradoException {
        return servicioService.updateServicio(servicio,id);
    }


}
