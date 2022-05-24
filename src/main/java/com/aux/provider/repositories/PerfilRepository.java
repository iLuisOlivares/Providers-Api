package com.aux.provider.repositories;

import com.aux.provider.models.PerfilModel;
import com.aux.provider.models.UsuarioModel;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface PerfilRepository extends CrudRepository<PerfilModel, Long> {
    public abstract UsuarioModel findByProveedor(long id_proveedor);
}
