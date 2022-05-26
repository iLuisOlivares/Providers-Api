package com.aux.provider.repositories;

import com.aux.provider.models.ServicioModel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ServicioRepository extends CrudRepository<ServicioModel, Long> {
    List<ServicioModel> findByProveedor_Id(Long aLong);
    List<ServicioModel> findByProveedor_IdAndActivo(Long aLong,boolean abool);
}
