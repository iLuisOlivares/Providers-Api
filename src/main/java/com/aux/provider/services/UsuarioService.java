package com.aux.provider.services;

import com.aux.provider.models.UsuarioModel;
import com.aux.provider.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;

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

    public UsuarioModel actualizarUsuario(UsuarioModel usuario, Long usuarioId) {
        UsuarioModel usuarioDB = usuarioRepository.findById(usuarioId).get();

        if (Objects.nonNull(usuario.getEmail()) && !"".equalsIgnoreCase(usuario.getEmail())) {
            usuarioDB.setEmail(usuario.getEmail());
        }

        if (Objects.nonNull(usuario.getClave()) && !"".equalsIgnoreCase(usuario.getClave())) {
            usuarioDB.setClave(usuario.getClave());
        }

        return usuarioRepository.save(usuarioDB);
    }

    public ArrayList<UsuarioModel> obtenerPorEmail(String email){
        return usuarioRepository.findByEmail(email);
    }

    public boolean eliminarUsuario(Long id){
        try{
            usuarioRepository.deleteById(id);
            return true;
        }catch (Exception err){
            return false;
        }
    }

}
