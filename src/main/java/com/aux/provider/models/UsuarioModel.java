package com.aux.provider.models;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuario")
public class UsuarioModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    @Column(unique = true, nullable = false)
    private String email;
    private String clave;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "usuario", fetch = FetchType.LAZY)
    private ProveedorModel proveedor;

    private boolean activo;


}
