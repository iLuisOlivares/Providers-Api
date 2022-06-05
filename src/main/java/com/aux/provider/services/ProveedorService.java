package com.aux.provider.services;

import com.aux.provider.models.PerfilModel;
import com.aux.provider.models.ProveedorModel;
import com.aux.provider.models.ServicioModel;
import com.aux.provider.models.UsuarioModel;
import com.aux.provider.repositories.ProveedorRepository;
import com.aux.provider.repositories.UsuarioRepository;
import com.aux.provider.services.exceptions.NoEncontradoException;
import com.aux.provider.services.interfaces.ProveedorInterfaceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ProveedorService implements ProveedorInterfaceService{

    //logica de servicios de los proveedores
    @Autowired
    private final ProveedorRepository proveedorRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ProveedorModel getProveedorbyEmail(String email) throws NoEncontradoException {
        log.info("Buscando proveedor con email: {}", email);
        //Invoca al repositorio para encontrrar al proveedor por medio del usuario y su atributo email
        return proveedorRepository.findByUsuario_Email(email).orElseThrow(
                () -> new NoEncontradoException("Proveedor no existe")
        );
    }

    @Override
    public List<ProveedorModel> getProveedores() {
        log.info("Recuperando proveedores de la base de datos");
        //Invoca al repositorio para encontrar todos los proveedores
        return (List<ProveedorModel>) proveedorRepository.findAll();
    }

    @Override
    public ProveedorModel saveProveedor(ProveedorModel proveedorModel) {
        log.info("Guardando Proveedor en la base de datos");
        //Encriptacion de la clave del usuario
        proveedorModel.getUsuario().setClave(passwordEncoder.encode(proveedorModel.getUsuario().getClave()));
        //Invoca al repositorio para realizar la consulta de guardado
        return proveedorRepository.save(proveedorModel);
    }

    @Override
    public ProveedorModel getProveedor(long id) throws NoEncontradoException {
        log.info("Buscando proveedor con id: {}", id);
        //Invoca al repositorio para encontrar al proveedor por medio del id
        return proveedorRepository.findById(id).orElseThrow(
                () -> new NoEncontradoException("Proveedor no existe")
        );
    }


}
