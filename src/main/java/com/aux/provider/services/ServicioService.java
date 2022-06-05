package com.aux.provider.services;

import com.aux.provider.models.ProveedorModel;
import com.aux.provider.models.ServicioModel;
import com.aux.provider.repositories.ProveedorRepository;
import com.aux.provider.repositories.ServicioRepository;
import com.aux.provider.services.exceptions.NoEncontradoException;
import com.aux.provider.services.interfaces.ServicioServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ServicioService implements ServicioServiceInterface {
    @Autowired
    ServicioRepository servicioRepository;
    @Autowired
    ProveedorRepository proveedorRepository;
    @Autowired
    ProveedorService proveedorService;


    //Logica para obtener lista de servicios
    @Override
    public ArrayList<ServicioModel> getServicios(){
        return (ArrayList< ServicioModel>) servicioRepository.findAll();
    }

    //Logica para la Actualizar(Guardar, Modificar, Eliminar) un servicio
    @Override
    public ServicioModel updateServicio(ServicioModel servicio, Long id) throws NoEncontradoException {
            log.info("Guardando servicio en la base de datos {}", servicio);
        //Verificacion si el servicio existe
        if(servicioRepository.existsById(servicio.getId())){
                //Si tiene un estado activo
                    log.info("Modificando servicio del proveedor con id: {}", id);
                    //si existe y tiene un estado activo igual a true lo buscamos
                    // y procedemos a modificar los respectivos atributos
                    ServicioModel servicioOld = this.getServicio(servicio.getId());
                    servicioOld.setServicio(servicio.getTitulo(),servicio.getArea_servicio(),
                    servicio.getServicio_especifico(),servicio.getDescripcion(),servicio.getPrecio());
                    //Invoca al repositorio para actualizar el servicio
                    return servicioRepository.save(servicioOld);


            }else {
                //Si no existe se procede a buscar al proveedor
                log.info("Buscando al proveedor con id: {}", id);
                ProveedorModel proveedor = proveedorService.getProveedor(id);
                log.info("Agregando servicio al proveedor");
                //Para posteriormente agregarle el servicio al proveedor
                servicio.setProveedor(proveedor);
                log.info("Guardando servicio en la base de datos");
                //Invoca al repositorio para guardar el nuevo servicio
                return servicioRepository.save(servicio);

            }
        }

    //Logica para la Obtener un servicio por su id
    @Override
    public ServicioModel getServicio(Long id) throws NoEncontradoException {
        //Invoca al repositorio para realizar la consulta de un servicio por id
        return servicioRepository.findById(id).orElseThrow( ()-> new NoEncontradoException("Servicio no encontrado"));
    }

    //Logica para la Obtener un servicio por el id del proveedor
    @Override
    public List<ServicioModel> getServiciosByProveedor(Long id) {
        //Invoca al repositorio para realizar la consulta de un servicio por el id del proveedor
        return servicioRepository.findByProveedor_Id(id);
    }

    //Logica para la Obtener un servicio por el id del proveedor y si este se encuentra activo
    @Override
    public List<ServicioModel> getServiciosProv(Long id) {
        //Invoca al repositorio para realizar la consulta de un servicio por el id del proveedor
        // y verificar que el servicio este activo
        return servicioRepository.findByProveedor_IdAndActivo(id,true);
    }
}
