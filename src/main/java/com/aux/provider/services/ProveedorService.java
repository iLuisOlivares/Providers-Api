package com.aux.provider.services;

import com.aux.provider.models.PerfilModel;
import com.aux.provider.models.ProveedorModel;
import com.aux.provider.models.UsuarioModel;
import com.aux.provider.repositories.PerfilRepository;
import com.aux.provider.repositories.ProveedorRepository;
import com.aux.provider.repositories.UsuarioRepository;
import com.aux.provider.services.exceptions.NoEncontradoException;
import com.aux.provider.services.interfaces.ProveedorInterfaceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service @Transactional @Slf4j @RequiredArgsConstructor
public class ProveedorService implements ProveedorInterfaceService, UserDetailsService {
    private final ProveedorRepository proveedorRepository;
    private final PerfilRepository perfilRepository;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UsuarioModel usuarioModel = usuarioRepository.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException("Usuario no encontrado"));

            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_USUARIO"));
        return new org.springframework.security.core.userdetails.User(usuarioModel.getEmail(), usuarioModel.getClave(), authorities);

    }


    @Override
    public ProveedorModel saveProveedor(ProveedorModel proveedorModel) {
        log.info("Guardando Proveedor en la base de datos");
        return proveedorRepository.save(proveedorModel);
    }

    @Override
    public PerfilModel savePerfil(PerfilModel perfilModel) {
       log.info("Guardando Perfil en la base de datos");
       return  perfilRepository.save(perfilModel);
    }

    @Override
    public UsuarioModel saveUsuario(UsuarioModel usuarioModel) {
        log.info("Guardando Usuario en la base de datos");
        usuarioModel.setClave(passwordEncoder.encode(usuarioModel.getClave()));
        return usuarioRepository.save(usuarioModel);
    }


    public PerfilModel setPerfilToProveedor(long id_perfil, long id_proveedor) throws NoEncontradoException {
        log.info("Agregando perfil al proveedor con id: {}", id_proveedor);
        ProveedorModel proveedor = this.getProveedor(id_proveedor);
        PerfilModel perfil = this.getPerfil(id_perfil);
        perfil.setProveedor(proveedor);
        log.info(perfil.getProveedor().getTipo_id());
        return perfil;

    }

    @Override
    public UsuarioModel setUsuarioToProveedor(String email, long id_proveedor) throws NoEncontradoException {
        log.info("Agregando usuario al proveedor con id: {}", id_proveedor);
        ProveedorModel proveedor = this.getProveedor(id_proveedor);
        UsuarioModel usuario = this.getUsuario(email);
        usuario.setProveedor(proveedor);
        log.info(usuario.getProveedor().getTipo_id());
        return usuario;
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
        log.info("Recuperando proveedores de la base de datos");
        return (List<ProveedorModel>) proveedorRepository.findAll();
    }


}
