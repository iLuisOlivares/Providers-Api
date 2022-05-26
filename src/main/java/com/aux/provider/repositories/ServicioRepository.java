package com.aux.provider.repositories;

import com.aux.provider.models.ServicioModel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ServicioRepository extends CrudRepository<ServicioModel, Long> {

    //Consulta SQL a la base de datos para encontrar un Servicio a partir del id del proveedor
    List<ServicioModel> findByProveedor_Id(Long aLong);

    //Consulta SQL a la base de datos para encontrar un servicio
    // a partir del id del proveedor y si este se encuentra activo
    List<ServicioModel> findByProveedor_IdAndActivo(Long aLong,boolean abool);
}
