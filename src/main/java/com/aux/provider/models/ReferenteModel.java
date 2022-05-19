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
    private long celular;
    private long email;
   /* @OneToOne(cascade = CascadeType.ALL, mappedBy = "referente", fetch = FetchType.LAZY)
    private ProveedorModel proveedorModel; */



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

    public long getCelular() {
        return celular;
    }

    public void setCelular(long celular) {
        this.celular = celular;
    }

    public long getEmail() {
        return email;
    }

    public void setEmail(long email) {
        this.email = email;
    }

}
