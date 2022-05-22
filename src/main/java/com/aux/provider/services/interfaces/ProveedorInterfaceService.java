package com.aux.provider.services.interfaces;

import com.aux.provider.models.PerfilModel;
import com.aux.provider.models.ProveedorModel;
import com.aux.provider.models.UsuarioModel;
import com.aux.provider.services.exceptions.NoEncontradoException;

import java.util.List;

public interface ProveedorInterfaceService {
    ProveedorModel saveProveedor(ProveedorModel proveedorModel);
    PerfilModel setPerfilToProveedor(long id_perfil,long id_proveedor) throws NoEncontradoException;
    UsuarioModel setUsuarioToProveedor(String email, long id) throws NoEncontradoException;
    ProveedorModel getProveedor(long id) throws NoEncontradoException;
    List<ProveedorModel> getProveedores();

}
