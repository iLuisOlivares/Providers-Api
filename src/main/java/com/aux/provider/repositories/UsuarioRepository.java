package com.aux.provider.repositories;

import com.aux.provider.models.UsuarioModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends CrudRepository<UsuarioModel,Long> {
    public Optional<UsuarioModel> findByEmail(String email);
    public abstract UsuarioModel findFirst1ByEmail(String email);
    public abstract UsuarioModel findFirst1ByEmailAndClave(String email, String clave);
}
