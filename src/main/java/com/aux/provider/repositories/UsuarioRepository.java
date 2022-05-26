package com.aux.provider.repositories;

import com.aux.provider.models.UsuarioModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends CrudRepository<UsuarioModel,Long> {

    //Consulta SQL a la base de datos para encontrar un Usuario a partir del email
    public Optional<UsuarioModel> findByEmail(String email);
}
