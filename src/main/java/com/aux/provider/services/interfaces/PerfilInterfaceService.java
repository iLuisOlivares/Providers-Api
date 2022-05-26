package com.aux.provider.services.interfaces;

import com.aux.provider.models.PerfilModel;
import com.aux.provider.models.UsuarioModel;
import com.aux.provider.services.exceptions.NoEncontradoException;

import java.util.ArrayList;

public interface PerfilInterfaceService {

    ArrayList<PerfilModel> getPerfil();

    PerfilModel getPerfil(long id) throws NoEncontradoException;
    PerfilModel getPerfilByProveedor(long id) throws NoEncontradoException;
    PerfilModel savePerfil(PerfilModel perfilModel);
    PerfilModel updatePerfil(PerfilModel nperfil, Long proveedor_id) throws NoEncontradoException;
}
