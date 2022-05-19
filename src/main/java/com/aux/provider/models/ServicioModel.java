package com.aux.provider.models;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name = "servicio")
public class ServicioModel {
    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(unique = true, nullable = false)
    private long id;
    private String titulo;
    private String area_servicio;
    private String servicio_especifico;
    private String descripcion;
    private long precio;
    private boolean activo;
    @ManyToOne
    @JoinColumn(name="proveedor_id", nullable=false)
    private ProveedorModel proveedor;

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public ProveedorModel getProveedor() {
        return proveedor;
    }

    public void setProveedor(ProveedorModel proveedor) {
        this.proveedor = proveedor;
    }

    public ServicioModel() {
    }

    public long getPrecio() {
        return precio;
    }

    public void setPrecio(long precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getArea_servicio() {
        return area_servicio;
    }

    public void setArea_servicio(String area_servicio) {
        this.area_servicio = area_servicio;
    }

    public String getServicio_especifico() {
        return servicio_especifico;
    }

    public void setServicio_especifico(String servicio_especifico) {
        this.servicio_especifico = servicio_especifico;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


}
