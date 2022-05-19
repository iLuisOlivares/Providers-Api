package com.aux.provider.services;

import com.aux.provider.models.PerfilModel;
import com.aux.provider.models.ProveedorModel;
import com.aux.provider.models.UsuarioModel;
import com.aux.provider.repositories.PerfilRepository;
import com.aux.provider.repositories.ProveedorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service @Transactional @Slf4j @RequiredArgsConstructor
public class ProveedorService implements ProveedorIntService {
    private final ProveedorRepository proveedorRepository;
    private final PerfilRepository perfilRepository;
    private final UsuarioModel usuarioModel;

    public ProveedorService() {
    }


    @Override
    public ProveedorModel saveProveedor(ProveedorModel proveedorModel) {
        return
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
