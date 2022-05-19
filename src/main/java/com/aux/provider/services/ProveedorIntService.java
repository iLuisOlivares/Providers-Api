package com.aux.provider.services;

import com.aux.provider.models.PerfilModel;
import com.aux.provider.models.ProveedorModel;

import java.util.List;

public interface ProveedorIntService {
    ProveedorModel saveProveedor(ProveedorModel proveedorModel);
    PerfilModel savePerfil(PerfilModel perfilModel);
    void setPerfilToProveedor(String email,long id);
    ProveedorModel getProveedor(String email);
    List<ProveedorModel> getProveedores();
}
