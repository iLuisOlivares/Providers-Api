package com.aux.provider.services;

import com.aux.provider.models.PerfilModel;
import com.aux.provider.models.ProveedorModel;
import com.aux.provider.models.UsuarioModel;
import com.aux.provider.repositories.PerfilRepository;
import com.aux.provider.repositories.ProveedorRepository;
import com.aux.provider.repositories.UsuarioRepository;
import com.aux.provider.services.exceptions.NoEncontradoException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service @Transactional @Slf4j @RequiredArgsConstructor
public class ProveedorService implements ProveedorIntService {
    private final ProveedorRepository proveedorRepository;
    private final PerfilRepository perfilRepository;
    private final UsuarioRepository usuarioRepository;


    @Override
    public ProveedorModel saveProveedor(ProveedorModel proveedorModel) {
        return proveedorRepository.save(proveedorModel);
    }

    @Override
    public PerfilModel savePerfil(PerfilModel perfilModel) {
       return  perfilRepository.save(perfilModel);
    }

    @Override
    public UsuarioModel saveUsuario(UsuarioModel usuarioModel) {
        return usuarioRepository.save(usuarioModel);
    }

    public ProveedorModel setPerfilToProveedor(long id_proveedor, long id_perfil) throws NoEncontradoException {
        log.info("Agregando perfil al proveedor con id: {}", id_proveedor);
        ProveedorModel proveedor = this.getProveedor(id_proveedor);
        PerfilModel perfil = this.getPerfil(id_perfil);

        proveedor.setPerfil(perfil);

        return proveedor;

    }

    @Override
    public void setUsuarioToProveedor(String email, long id) {

    }

    @Override
    public ProveedorModel getProveedor(long id) throws NoEncontradoException {
       log.info("Buscando proveedor con id: {}", id);
       return proveedorRepository.findById(id).orElseThrow(
               () -> new NoEncontradoException("Usuario no existe")
       );
    }


    @Override
    public PerfilModel getPerfil(long id) throws NoEncontradoException {
        log.info("Buscando usuario con id: {}", id);
        return perfilRepository.findById(id).orElseThrow(
                () -> new NoEncontradoException(String.format("Perfil no existe"))
       );
    }

    @Override
    public UsuarioModel getUsuario(String email) throws NoEncontradoException {
        log.info("Buscando usuario con email: {}", email);
        return usuarioRepository.findByEmail(email).orElseThrow(
                () -> new NoEncontradoException(String.format("Perfil no existe"))
        );
    }

    @Override
    public List<ProveedorModel> getProveedores() {
        return null;
    }
}
