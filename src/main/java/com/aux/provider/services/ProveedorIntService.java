package com.aux.provider.services;

import com.aux.provider.models.PerfilModel;
import com.aux.provider.models.ProveedorModel;
import com.aux.provider.models.UsuarioModel;
import com.aux.provider.services.exceptions.NoEncontradoException;

import java.util.List;

public interface ProveedorIntService {
    ProveedorModel saveProveedor(ProveedorModel proveedorModel);
    PerfilModel savePerfil(PerfilModel perfilModel);
    UsuarioModel saveUsuario(UsuarioModel usuarioModel);
    void setPerfilToProveedor(String email,long id);
    void setUsuarioToProveedor(String email,long id);
    ProveedorModel getProveedor(long id) throws NoEncontradoException;
    PerfilModel getPerfil(long id) throws NoEncontradoException;
    UsuarioModel getUsuario(String email) throws NoEncontradoException;
    List<ProveedorModel> getProveedores();

}
