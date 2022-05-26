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
    @Autowired
    PerfilRepository perfilRepository;
    @Autowired
    ProveedorRepository proveedorRepository;
    @Autowired
    ProveedorService proveedorService;

    public ArrayList<PerfilModel> getPerfil(){
        return (ArrayList< PerfilModel>) perfilRepository.findAll();
    }



    @Override
    public PerfilModel savePerfil(PerfilModel perfilModel) {
        log.info("Guardando Perfil en la base de datos");
        return  perfilRepository.save(perfilModel);
    }
    @Override
    public PerfilModel updatePerfil(PerfilModel nperfil, Long proveedor_id) throws NoEncontradoException {
        log.info(String.valueOf(proveedor_id));
        PerfilModel perfil = perfilRepository.findFirst1ByProveedor_Id(proveedor_id).orElseThrow(
                () -> new NoEncontradoException("Perfil no existe")
        );
        perfil.setPerfil(nperfil.getNombre(),nperfil.getApellidos(),
                nperfil.getDireccion(), nperfil.getCiudad() ,nperfil.getCelular(),nperfil.getFoto(),
                nperfil.getDescripcion(), nperfil.getPagina_web());

        log.info("Guardando Perfil en la base de datos");
        return  perfilRepository.save(perfil);
    }

    @Override
    public PerfilModel getPerfil(long id) throws NoEncontradoException {
        log.info("Buscando perfil con id: {}", id);
        return perfilRepository.findById(id).orElseThrow(
                () -> new NoEncontradoException("Perfil no existe")
        );
    }

    public PerfilModel getPerfilByProveedor(long id) throws NoEncontradoException {
        log.info("Buscando perfil de proveedor con id: {}", id);
        return perfilRepository.findFirst1ByProveedor_Id(id).orElseThrow(
                () -> new NoEncontradoException("Perfil no existe")
        );
    }

    public PerfilModel setPerfilToProveedor(long id_perfil, long id_proveedor) throws NoEncontradoException {
        log.info("Agregando perfil al proveedor con id: {}", id_proveedor);
        ProveedorModel proveedor = proveedorService.getProveedor(id_proveedor);
        PerfilModel perfil = this.getPerfil(id_perfil);
        perfil.setProveedor(proveedor);
        log.info(perfil.getProveedor().getTipo_id());
        return perfil;

    }


    /*perfil.setPerfil(nperfil.getNombre(),nperfil.getApellidos(),
                nperfil.getDireccion(),nperfil.getCelular(),nperfil.getFoto(),
                nperfil.getDescripcion(), nperfil.getPagina_web());*/
        /*if (Objects.nonNull(perfil.getApellidos()) && !"".equalsIgnoreCase(perfil.getApellidos())){
            oldPerfil.setApellidos(perfil.getApellidos());
        }
        if (!"".equalsIgnoreCase(String.valueOf(perfil.getCelular()))){
            oldPerfil.setCelular(perfil.getCelular());
        }
        if (Objects.nonNull(perfil.getDescripcion()) && !"".equalsIgnoreCase(perfil.getDescripcion())){
            oldPerfil.setDescripcion(perfil.getDescripcion());
        }
        if (Objects.nonNull(perfil.getDireccion()) && !"".equalsIgnoreCase(perfil.getDireccion())){
            oldPerfil.setDireccion(perfil.getDireccion());
        }
        if (Objects.nonNull(perfil.getFoto()) && !"".equalsIgnoreCase(perfil.getFoto())){
            oldPerfil.setApellidos(perfil.getFoto());
        }
        if (Objects.nonNull(perfil.getNombre()) && !"".equalsIgnoreCase(perfil.getNombre())){
            oldPerfil.setNombre(perfil.getNombre());
        }
        if (Objects.nonNull(perfil.getPagina_web()) && !"".equalsIgnoreCase(perfil.getPagina_web())){
            oldPerfil.setPagina_web(perfil.getPagina_web());
        }*/
}
