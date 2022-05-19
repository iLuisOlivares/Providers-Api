package com.aux.provider.services;

import com.aux.provider.models.ServicioModel;
import com.aux.provider.repositories.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
@Service
public class ServicioService {
    @Autowired
    ServicioRepository servicioRepository;

    public ArrayList<ServicioModel> obtenerServicios(){
        return (ArrayList< ServicioModel>) servicioRepository.findAll();
    }

    public boolean guardarServicio(ServicioModel servicio){
        boolean servicioDB = servicioRepository.existsById(servicio.getId());
        if(servicioDB){
            return false;
        }else{
            servicioRepository.save(servicio);
            return true;
        }
    }

    public Optional<ServicioModel> obtenerPorId(Long id){
        return servicioRepository.findById(id);
    }

}
