package com.aux.provider.models;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "proveedor")
public class ProveedorModel {
    @Id
    @GeneratedValue( strategy=GenerationType.AUTO )
    private long id;
    private String tipo_id;
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false, unique = true)
    private UsuarioModel usuario;
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "perfil_id", nullable = false, unique = true)
    private PerfilModel perfil;
    /*@OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "referente_id", nullable = false, unique = true)
    private ReferenteModel referente;*/

    @OneToMany(mappedBy = "proveedor")
    private List<ServicioModel> servicios;


    public PerfilModel getPerfil() {
        return perfil;
    }

    public void setPerfil(PerfilModel perfil) {
        this.perfil = perfil;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTipo_id() {
        return tipo_id;
    }

    public void setTipo_id(String tipo_id) {
        this.tipo_id = tipo_id;
    }

    public UsuarioModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioModel usuario) {
        this.usuario = usuario;
    }

    public List<ServicioModel> getServicios() {
        return servicios;
    }

    public void setServicios(List<ServicioModel> servicios) {
        this.servicios = servicios;
    }
}
