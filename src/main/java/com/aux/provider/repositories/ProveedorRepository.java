package com.aux.provider.repositories;

import com.aux.provider.models.ProveedorModel;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProveedorRepository extends CrudRepository<ProveedorModel, Long> {

    /*Consulta SQL a la base de datos para encontrar un Proveedor
    a partir del email desde la clase/modeloUsuario*/
    Optional<ProveedorModel> findByUsuario_Email(String email);
}
