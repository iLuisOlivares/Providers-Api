package com.aux.provider.models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "perfil")
public class PerfilModel {

    private String nombre;
    private String apellidos;
    private String direccion;
    private long celular;
    private String foto;
    private String descripcion;
    private String pagina_web;
    private ReferenteModel referente_personal;
    private ReferenteModel referente_familiar;


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public long getCelular() {
        return celular;
    }

    public void setCelular(long celular) {
        this.celular = celular;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPagina_web() {
        return pagina_web;
    }

    public void setPagina_web(String pagina_web) {
        this.pagina_web = pagina_web;
    }


    public ReferenteModel getReferente_personal() {
        return referente_personal;
    }

    public void setReferente_personal(ReferenteModel referente_personal) {
        this.referente_personal = referente_personal;
    }

    public ReferenteModel getReferente_familiar() {
        return referente_familiar;
    }

    public void setReferente_familiar(ReferenteModel referente_familiar) {
        this.referente_familiar = referente_familiar;
    }
}
