package com.aux.provider.services.interfaces;

import com.aux.provider.models.PerfilModel;
import com.aux.provider.services.exceptions.NoEncontradoException;

public interface PerfilInterfaceService {
    PerfilModel savePerfil(PerfilModel perfilModel);
    PerfilModel getPerfil(long id) throws NoEncontradoException;
}