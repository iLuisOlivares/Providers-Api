package com.aux.provider.services;

import com.aux.provider.models.ProveedorModel;
import com.aux.provider.models.ServicioModel;
import com.aux.provider.repositories.ProveedorRepository;
import com.aux.provider.repositories.ServicioRepository;
import com.aux.provider.services.exceptions.NoEncontradoException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ServicioService {
    @Autowired
    ServicioRepository servicioRepository;
    @Autowired
    ProveedorRepository proveedorRepository;
    @Autowired
    ProveedorService proveedorService;


    public ArrayList<ServicioModel> getServicios(){
        return (ArrayList< ServicioModel>) servicioRepository.findAll();
    }

    public ServicioModel saveServicio(ServicioModel servicio,long id_proveedor) throws NoEncontradoException {
        log.info("Buscando perfil al proveedor con id: {}", id_proveedor);
        ProveedorModel proveedor = proveedorService.getProveedor(id_proveedor);
        log.info("Agregando servicio al proveedor");
        servicio.setProveedor(proveedor);
        log.info("Guardando servicio en la base de datos");
        return servicioRepository.save(servicio);
    }
        public ServicioModel updateServicio(ServicioModel servicio, Long id) throws NoEncontradoException {
            log.info("Guardando servicio en la base de datos {}", servicio);
            if(servicioRepository.existsById(servicio.getId())){
                if(servicio.isActivo()){
                    log.info("Modificando servicio del proveedor con id: {}", id);
                    ServicioModel servicioOld = this.getServicio(servicio.getId());
                    servicioOld.setServicio(servicio.getTitulo(),servicio.getArea_servicio(),
                    servicio.getServicio_especifico(),servicio.getDescripcion(),servicio.getPrecio());
                    return servicioRepository.save(servicioOld);

                }else{
                    log.info("Desactivando servicio del proveedor con id: {}", id);
                    ServicioModel servicioOld = this.getServicio(servicio.getId());
                    servicioOld.setActivo(servicio.isActivo());
                    return servicioRepository.save(servicioOld);
                }
            }else {
                log.info("Buscando al proveedor con id: {}", id);
                ProveedorModel proveedor = proveedorService.getProveedor(id);
                log.info("Agregando servicio al proveedor");
                servicio.setProveedor(proveedor);
                log.info("Guardando servicio en la base de datos");
                return servicioRepository.save(servicio);

            }
        }


    public ServicioModel getServicio(Long id) throws NoEncontradoException {
        return servicioRepository.findById(id).orElseThrow( ()-> new NoEncontradoException("Servicio no encontrado"));
    }

    public List<ServicioModel> getServiciosByProveedor(Long id) {
        return servicioRepository.findByProveedor_Id(id);
    }

    public List<ServicioModel> getServiciosProv(Long id) {
        return servicioRepository.findByProveedor_IdAndActivo(id,true);
    }
}
