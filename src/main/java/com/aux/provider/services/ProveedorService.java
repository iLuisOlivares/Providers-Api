package com.aux.provider.services;

import com.aux.provider.models.PerfilModel;
import com.aux.provider.models.ProveedorModel;
import com.aux.provider.models.UsuarioModel;
import com.aux.provider.repositories.PerfilRepository;
import com.aux.provider.repositories.ProveedorRepository;
import com.aux.provider.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProveedorService implements ProveedorIntService {


    @Override
    public ProveedorModel saveProveedor(ProveedorModel proveedorModel) {
        return null;
    }

    @Override
    public PerfilModel savePerfil(PerfilModel perfilModel) {
        return null;
    }

    @Override
    public void setPerfilToProveedor(String email, long id) {

    }

    @Override
    public ProveedorModel getProveedor(String email) {
        return null;
    }

    @Override
    public List<ProveedorModel> getProveedores() {
        return null;
    }
}
