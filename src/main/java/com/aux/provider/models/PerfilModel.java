package com.aux.provider.models;

import javax.persistence.*;
import static javax.persistence.FetchType.EAGER;

@Entity
@Table(name = "perfil")
public class PerfilModel {

    @Id
    @GeneratedValue( strategy= GenerationType.AUTO )
    @Column(unique = true, nullable = false)
    private long id;
    private String nombre;
    private String apellidos;
    private String direccion;
    private long celular;
    private String foto;
    private String descripcion;
    private String pagina_web;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "perfil", fetch = FetchType.LAZY)
    private ProveedorModel proveedor;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ProveedorModel getProveedor() {
        return proveedor;
    }

    public void setProveedor(ProveedorModel proveedor) {
        this.proveedor = proveedor;
    }

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


}
