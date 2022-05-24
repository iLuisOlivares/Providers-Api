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
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service @Transactional @Slf4j @RequiredArgsConstructor
public class ProveedorService implements ProveedorInterfaceService{
    @Autowired
    private final ProveedorRepository proveedorRepository;
    private final UsuarioRepository usuarioRepository;

   /* @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UsuarioModel usuarioModel = usuarioRepository.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException("Usuario no encontrado"));

            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_USUARIO"));
        return new org.springframework.security.core.userdetails.User(usuarioModel.getEmail(), usuarioModel.getClave(), authorities);
    }*/

    @Override
    public ProveedorModel saveProveedor(ProveedorModel proveedorModel) {
        log.info("Guardando Proveedor en la base de datos");
        return proveedorRepository.save(proveedorModel);
    }

    @Override
    public ProveedorModel getProveedor(long id) throws NoEncontradoException {
       log.info("Buscando proveedor con id: {}", id);
       return proveedorRepository.findById(id).orElseThrow(
               () -> new NoEncontradoException("Usuario no existe")
       );
    }

    @Override
    public List<ProveedorModel> getProveedores() {
        log.info("Recuperando proveedores de la base de datos");
        return (List<ProveedorModel>) proveedorRepository.findAll();
    }





}
