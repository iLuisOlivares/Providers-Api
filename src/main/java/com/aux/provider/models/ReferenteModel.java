package com.aux.provider.models;

import javax.persistence.*;

@Entity
@Table(name = "referente")
public class ReferenteModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    private String nombre;
    private String tipo_referente;
    private long celular;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "referente", fetch = FetchType.LAZY)
    private ProveedorModel proveedorModel;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo_referente() {
        return tipo_referente;
    }

    public void setTipo_referente(String tipo_referente) {
        this.tipo_referente = tipo_referente;
    }

    public long getCelular() {
        return celular;
    }

    public void setCelular(long celular) {
        this.celular = celular;
    }

    public ProveedorModel getProveedorModel() {
        return proveedorModel;
    }

    public void setProveedorModel(ProveedorModel proveedorModel) {
        this.proveedorModel = proveedorModel;
    }
}
