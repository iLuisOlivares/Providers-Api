package com.aux.provider.services;

import com.aux.provider.models.ProveedorModel;
import com.aux.provider.models.UsuarioModel;
import com.aux.provider.repositories.UsuarioRepository;
import com.aux.provider.services.exceptions.NoEncontradoException;
import com.aux.provider.services.interfaces.UsuarioInterfaceService;
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
import java.util.Objects;
import java.util.Optional;

@Service @Transactional
@Slf4j
@RequiredArgsConstructor
public class UsuarioService implements UsuarioInterfaceService, UserDetailsService {
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    ProveedorService proveedorService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UsuarioModel usuarioModel = usuarioRepository.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException("Usuario no encontrado"));
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USUARIO"));
        return new org.springframework.security.core.userdetails.User(usuarioModel.getEmail(), usuarioModel.getClave(), authorities);
    }
    public ArrayList<UsuarioModel> obtenerUsuarios(){
       return(ArrayList<UsuarioModel>) usuarioRepository.findAll();
    }

    public boolean guardarUsuario(UsuarioModel usuario){
        boolean usuarioDB = usuarioRepository.existsById(usuario.getId());
        if(usuarioDB){
            return false;
        }else{
             usuarioRepository.save(usuario);
             return true;
        }
    }

    public Optional<UsuarioModel> obtenerPorId(Long id){
        return usuarioRepository.findById(id);
    }

    public UsuarioModel updateUsuario(UsuarioModel usuario, Long usuarioId) {
        UsuarioModel usuarioDB = usuarioRepository.findById(usuarioId).get();

        if (Objects.nonNull(usuario.getEmail()) && !"".equalsIgnoreCase(usuario.getEmail())) {
            usuarioDB.setEmail(usuario.getEmail());
        }

        if (Objects.nonNull(usuario.getClave()) && !"".equalsIgnoreCase(usuario.getClave())) {
            usuarioDB.setClave(passwordEncoder.encode(usuario.getClave()));
        }

        return usuarioRepository.save(usuarioDB);
    }


    @Override
    public UsuarioModel saveUsuario(UsuarioModel usuarioModel) {
        log.info("Guardando Usuario en la base de datos:{}", usuarioModel);
        usuarioModel.setClave(passwordEncoder.encode(usuarioModel.getClave()));
        return usuarioRepository.save(usuarioModel);
    }

    @Override
    public UsuarioModel getUsuario(String email) throws NoEncontradoException {
        log.info("Buscando usuario con email: {}", email);
        return usuarioRepository.findByEmail(email).orElseThrow(
                () -> new NoEncontradoException("Usuario no existe")
        );
    }


}
