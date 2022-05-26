package com.aux.provider.services.interfaces;

import com.aux.provider.models.PerfilModel;
import com.aux.provider.models.ProveedorModel;
import com.aux.provider.models.UsuarioModel;
import com.aux.provider.services.exceptions.NoEncontradoException;

import java.util.List;

public interface ProveedorInterfaceService {

    ProveedorModel getProveedor(long id) throws NoEncontradoException;
    List<ProveedorModel> getProveedores();
    ProveedorModel getProveedorbyEmail(String email) throws NoEncontradoException;
    ProveedorModel saveProveedor(ProveedorModel proveedorModel);

}
