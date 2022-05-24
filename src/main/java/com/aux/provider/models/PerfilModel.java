package com.aux.provider.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "perfil")
public class PerfilModel {

    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(unique = true, nullable = false)
    private long id;
    private String nombre;
    private String apellidos;
    private String direccion;
    private String ciudad;
    @Column(unique = true, nullable = true)
    private long celular;
    private String foto;
    private String descripcion;
    private String pagina_web;

    @OneToOne(fetch=FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "proveedor_id", nullable = true, unique = true)
    private ProveedorModel proveedor;

    public PerfilModel(String nombre, String apellidos, String direccion, String ciudad, long celular, String foto, String descripcion, String pagina_web) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.celular = celular;
        this.foto = foto;
        this.descripcion = descripcion;
        this.pagina_web = pagina_web;
    }

    public void setPerfil(String nombre, String apellidos, String direccion, long celular, String foto, String descripcion, String pagina_web) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.celular = celular;
        this.foto = foto;
        this.descripcion = descripcion;
        this.pagina_web = pagina_web;
    }
}
