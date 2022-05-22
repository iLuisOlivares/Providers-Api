package com.aux.provider.services;

import com.aux.provider.models.PerfilModel;
import com.aux.provider.models.UsuarioModel;
import com.aux.provider.repositories.PerfilRepository;
import com.aux.provider.services.exceptions.NoEncontradoException;
import com.aux.provider.services.interfaces.PerfilInterfaceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Objects;

@Service @Transactional
@Slf4j
@RequiredArgsConstructor
public class PerfilService implements PerfilInterfaceService {
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


    @Override
    public PerfilModel savePerfil(PerfilModel perfilModel) {
        log.info("Guardando Perfil en la base de datos");
        return  perfilRepository.save(perfilModel);
    }

    @Override
    public PerfilModel getPerfil(long id) throws NoEncontradoException {
        log.info("Buscando perfil con id: {}", id);
        return perfilRepository.findById(id).orElseThrow(
                () -> new NoEncontradoException("Perfil no existe")
        );
    }

    public PerfilModel actualizarPerfil(PerfilModel perfil, Long usuarioId) {
        PerfilModel perfilDB = perfilRepository.findById(usuarioId).get();

        if (Objects.nonNull(perfil.getApellidos()) && !"".equalsIgnoreCase(perfil.getApellidos())){
            perfilDB.setApellidos(perfil.getApellidos());
        }
        if (Objects.nonNull(perfil.getCelular())){
            perfilDB.setCelular(perfil.getCelular());
        }
        if (Objects.nonNull(perfil.getDescripcion()) && !"".equalsIgnoreCase(perfil.getDescripcion())){
            perfilDB.setDescripcion(perfil.getDescripcion());
        }
        if (Objects.nonNull(perfil.getDireccion()) && !"".equalsIgnoreCase(perfil.getDireccion())){
            perfilDB.setDireccion(perfil.getDireccion());
        }
        if (Objects.nonNull(perfil.getFoto()) && !"".equalsIgnoreCase(perfil.getFoto())){
            perfilDB.setApellidos(perfil.getFoto());
        }
        if (Objects.nonNull(perfil.getNombre()) && !"".equalsIgnoreCase(perfil.getNombre())){
            perfilDB.setNombre(perfil.getNombre());
        }
        if (Objects.nonNull(perfil.getPagina_web()) && !"".equalsIgnoreCase(perfil.getPagina_web())){
            perfilDB.setPagina_web(perfil.getPagina_web());
        }

        return perfilRepository.save(perfilDB);
    }
}
