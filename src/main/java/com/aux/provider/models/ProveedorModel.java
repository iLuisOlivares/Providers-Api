package com.aux.provider.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "proveedor")
public class ProveedorModel {
    @Id
    @GeneratedValue( strategy=GenerationType.AUTO )
    private long id;
    private String tipo_id;
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false, unique = true)
    private UsuarioModel usuario;
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "perfil_id", nullable = false, unique = true)
    private PerfilModel perfil;
    /*@OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "referente_id", nullable = false, unique = true)
    private ReferenteModel referente;*/

    @OneToMany(mappedBy = "proveedor")
    private List<ServicioModel> servicios;


}
