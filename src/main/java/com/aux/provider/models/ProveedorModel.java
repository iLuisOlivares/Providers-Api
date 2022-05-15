package com.aux.provider.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

import static javax.persistence.FetchType.EAGER;
@Entity
@Table(name = "proveedor")
public class ProveedorModel {
    private PerfilModel perfil;
    @ManyToMany(fetch = EAGER)
    private Collection<ServicioModel> servicios = new ArrayList<>();
    private String tipo;


    public PerfilModel getPerfil() {
        return perfil;
    }

    public void setPerfil(PerfilModel perfil) {
        this.perfil = perfil;
    }

    public Collection<ServicioModel> getServicios() {
        return servicios;
    }

    public void setServicios(Collection<ServicioModel> servicios) {
        this.servicios = servicios;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }


}
