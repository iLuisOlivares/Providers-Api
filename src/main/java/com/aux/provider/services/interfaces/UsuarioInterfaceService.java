package com.aux.provider.services.interfaces;

import com.aux.provider.models.PerfilModel;
import com.aux.provider.models.UsuarioModel;
import com.aux.provider.services.exceptions.NoEncontradoException;

public interface UsuarioInterfaceService {
    UsuarioModel saveUsuario(UsuarioModel usuarioModel);
    UsuarioModel getUsuario(String email) throws NoEncontradoException;
}
