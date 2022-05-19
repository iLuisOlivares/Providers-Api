package com.aux.provider.services;

import com.aux.provider.models.PerfilModel;
import com.aux.provider.repositories.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class PerfilService {
    @Autowired
    PerfilRepository perfilRepository;

    public ArrayList<PerfilModel> obtenerServicios(){
        return (ArrayList< PerfilModel>) perfilRepository.findAll();
    }

    public boolean guardarPerfil(PerfilModel perfil){
        boolean perfilDB = perfilRepository.existsById(perfil.getId());
        if(perfilDB){
            return false;
        }else{
            perfilRepository.save(perfil);
            return true;
        }
    }


}
