package com.aux.provider.repositories;

import com.aux.provider.models.UsuarioModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface UsuarioRepository extends CrudRepository<UsuarioModel,Long> {
    public abstract ArrayList<UsuarioModel> findByEmail(String email);
    public abstract ArrayList<UsuarioModel> findByClave(String clave);
}
