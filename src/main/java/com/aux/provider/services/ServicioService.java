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
        log.info("Guardando servicio en la base de datos");
        if(servicioRepository.existsById(id)){
          servicio = this.getServicio(id);
          servicio.setServicio(servicio.getTitulo(),servicio.getArea_servicio(),
                  servicio.getServicio_especifico(),servicio.getDescripcion(),servicio.getPrecio());
          return servicio;
        }else {
        return servicioRepository.save(servicio);
        }

    }

    public ServicioModel setServicioToProveedor(long id_servicio, long id_proveedor) throws NoEncontradoException {
        log.info("Agregando perfil al proveedor con id: {}", id_proveedor);
        ProveedorModel proveedor = proveedorService.getProveedor(id_proveedor);
        ServicioModel servicio = this.getServicio(id_servicio);
        servicio.setProveedor(proveedor);
        List<ServicioModel> listServicio = proveedor.getServicios();
        listServicio.add(servicio);
        proveedor.setServicios(listServicio);
        log.info(servicio.getProveedor().getTipo_id());
        return servicio;
    }

    public ServicioModel getServicio(Long id) throws NoEncontradoException {
        return servicioRepository.findById(id).orElseThrow( ()-> new NoEncontradoException("Servicio no encontrado"));
    }

}
