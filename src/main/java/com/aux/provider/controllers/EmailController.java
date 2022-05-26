package com.aux.provider.controllers;

import com.aux.provider.models.ServicioClienteModel;
import com.aux.provider.services.ServicioClienteService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
public class EmailController {

    // Invocamos los servicios(Acciones permitidas) que le corresponden al controlador
    @Autowired
    ServicioClienteService servicioClienteService;

    // Peticion index
    @GetMapping("/")
    public String index(){
        return "send email view";
    }

    // Peticion POST para el envió de Emails
    @PostMapping("/send")
    public String sendEmail(@RequestBody ServicioClienteModel servicioCliente){
        String content = servicioCliente.getBody() + "\n\nEmail del proveedor: " + servicioCliente.getEmail() + "\nIdentificacion del proveedor: " + servicioCliente.getUsuario_id();
        servicioClienteService.sendEmail(servicioCliente.getEmail(), "serviciotecnico.auxprovider@gmail.com", servicioCliente.getSubject(), content);
        return "Correo Electronico Enviado";
    }
}


