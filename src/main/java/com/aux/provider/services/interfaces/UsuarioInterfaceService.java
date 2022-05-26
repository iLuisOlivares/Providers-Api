package com.aux.provider.services.interfaces;

import com.aux.provider.models.PerfilModel;
import com.aux.provider.models.UsuarioModel;
import com.aux.provider.services.exceptions.NoEncontradoException;

import java.util.Optional;

public interface UsuarioInterfaceService {
    UsuarioModel saveUsuario(UsuarioModel usuarioModel);
    UsuarioModel getUsuario(String email) throws NoEncontradoException;
    Optional<UsuarioModel> obtenerPorId(Long id);
    UsuarioModel updateUsuario(UsuarioModel usuario, Long usuarioId);
}
