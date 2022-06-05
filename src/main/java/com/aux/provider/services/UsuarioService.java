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

    //Agregar un rol autoritario el usuario para poder hacer uso de peticiones protegidas
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UsuarioModel usuarioModel = usuarioRepository.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException("Usuario no encontrado"));
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USUARIO"));
        return new org.springframework.security.core.userdetails.User(usuarioModel.getEmail(), usuarioModel.getClave(), authorities);
    }

    //Logica Para la obtener una lista de los usuarios
    public ArrayList<UsuarioModel> obtenerUsuarios(){
        //Invoca al repositorio para realizar la consulta de todos los usuarios
        return(ArrayList<UsuarioModel>) usuarioRepository.findAll();
    }

    //Logica para la Obtener un usuario por su email
    @Override
    public UsuarioModel getUsuario(String email) throws NoEncontradoException {
        log.info("Buscando usuario con email: {}", email);
        //Invoca al repositorio para realizar la consulta de un usuario por email
        return usuarioRepository.findByEmail(email).orElseThrow(
                () -> new NoEncontradoException("Usuario no existe")
        );
    }

    //Logica para la obtener un usuario por su id
    @Override
    public Optional<UsuarioModel> obtenerPorId(Long id){
        //Invoca al repositorio para realizar la consulta y encontrar un usuario por id
        return usuarioRepository.findById(id);
    }

    //Logica para la actualizar un usuario por su id
    @Override
    public UsuarioModel updateUsuario(UsuarioModel usuario, Long usuarioId) {
        UsuarioModel usuarioDB = usuarioRepository.findById(usuarioId).get();
        //Comprobar que los campos no sean nulos para modificar al usuario
        if (Objects.nonNull(usuario.getEmail()) && !"".equalsIgnoreCase(usuario.getEmail())) {
            usuarioDB.setEmail(usuario.getEmail());
        }

        if (Objects.nonNull(usuario.getClave()) && !"".equalsIgnoreCase(usuario.getClave())) {
            usuarioDB.setClave(passwordEncoder.encode(usuario.getClave()));
        }

        //Posteriormente se invoca al repositorio para actualizarlo en la base de datos
        return usuarioRepository.save(usuarioDB);
    }


    //Logica para la guardar un usuario

    @Override
    public UsuarioModel saveUsuario(UsuarioModel usuarioModel) {
        log.info("Guardando Usuario en la base de datos:{}", usuarioModel);
        //Encripta la clave del usuario
        usuarioModel.setClave(passwordEncoder.encode(usuarioModel.getClave()));
        //Posteriormente se invoca al repositorio para guardarlo en la base de datos
        return usuarioRepository.save(usuarioModel);
    }




}
