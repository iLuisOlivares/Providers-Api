package com.aux.provider.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    private long celular;
    private String foto;
    private String descripcion;
    private String pagina_web;

    @OneToOne(fetch=FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "proveedor_id", nullable = true, unique = true)
    private ProveedorModel proveedor;




}
