package com.aux.provider.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import static javax.persistence.FetchType.EAGER;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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


}
