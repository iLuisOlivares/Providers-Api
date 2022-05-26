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
    @Autowired
    ServicioService servicioService;

    @GetMapping("/servicios")
    public ArrayList<ServicioModel> obtenerServicios(){return servicioService.getServicios();}

    @PostMapping("/servicios/update/{id}")
    public ServicioModel saveServicio(@RequestBody ServicioModel servicio, @PathVariable("id") Long id) throws NoEncontradoException {
        return servicioService.updateServicio(servicio, id);

    }
    @PutMapping("/servicios/update/{id}")
    public ServicioModel updateServicio(@RequestBody ServicioModel servicio, @PathVariable("id") Long id) throws NoEncontradoException {
        return servicioService.updateServicio(servicio,id);
    }

    @GetMapping("/servicios/{id}")
    public List<ServicioModel> getServicio(@PathVariable("id") Long id){
        return servicioService.getServiciosProv(id);
    }
}
