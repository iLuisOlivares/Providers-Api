package com.aux.provider.services;

import com.aux.provider.models.PerfilModel;
import com.aux.provider.models.ProveedorModel;
import com.aux.provider.repositories.PerfilRepository;
import com.aux.provider.repositories.ProveedorRepository;
import com.aux.provider.services.exceptions.NoEncontradoException;
import com.aux.provider.services.interfaces.PerfilInterfaceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service @Transactional
@Slf4j
@RequiredArgsConstructor
public class PerfilService implements PerfilInterfaceService {

    //Logica de servicios de los perfiles
    @Autowired
    PerfilRepository perfilRepository;
    @Autowired
    ProveedorRepository proveedorRepository;
    @Autowired
    ProveedorService proveedorService;

    @Override
    public ArrayList<PerfilModel> getPerfil(){

        //Invoca al repositorio para realizar la respectiva consulta
        return (ArrayList< PerfilModel>) perfilRepository.findAll();
    }

    @Override
    public PerfilModel getPerfil(long id) throws NoEncontradoException {
        log.info("Buscando perfil con id: {}", id);
        //Invoca al repositorio para obtener la consulta del perfil
        return perfilRepository.findById(id).orElseThrow(
                () -> new NoEncontradoException("Perfil no existe")
        );
    }

    @Override
    public PerfilModel getPerfilByProveedor(long id) throws NoEncontradoException {
        log.info("Buscando perfil de proveedor con id: {}", id);
        //Invoca al repositorio para obtener la consulta del perfil por el id del proveedor
        return perfilRepository.findFirst1ByProveedor_Id(id).orElseThrow(
                () -> new NoEncontradoException("Perfil no existe")
        );
    }

    @Override
    public PerfilModel updatePerfil(PerfilModel nperfil, Long proveedor_id) throws NoEncontradoException {
        log.info(String.valueOf(proveedor_id));
        //Invoca al repositorio para obtener la consulta del perfil
        PerfilModel perfil = perfilRepository.findFirst1ByProveedor_Id(proveedor_id).orElseThrow(
                () -> new NoEncontradoException("Perfil no existe")
        );
        //Una vez se realiza la consulta del perfil se procede a modificar los respectivos atributos
        perfil.setPerfil(nperfil.getNombre(),nperfil.getApellidos(),
                nperfil.getDireccion(), nperfil.getCiudad() ,nperfil.getCelular(),nperfil.getFoto(),
                nperfil.getDescripcion(), nperfil.getPagina_web());

        log.info("Guardando Perfil en la base de datos");
        //Invoca al repositorio para realizar la consulta de guardado
        return  perfilRepository.save(perfil);
    }

    @Override
    public PerfilModel savePerfil(PerfilModel perfilModel) {
        log.info("Guardando Perfil en la base de datos");
        //Invoca al repositorio para realizar la respectiva consulta
        return  perfilRepository.save(perfilModel);
    }

}
