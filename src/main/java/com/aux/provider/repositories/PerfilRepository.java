package com.aux.provider.repositories;

import com.aux.provider.models.PerfilModel;
import com.aux.provider.models.UsuarioModel;
import com.aux.provider.services.exceptions.NoEncontradoException;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface PerfilRepository extends CrudRepository<PerfilModel, Long> {
    Optional<PerfilModel> findFirst1ByProveedor_Id(long id_proveedor);


}
