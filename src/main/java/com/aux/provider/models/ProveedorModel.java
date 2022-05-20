package com.aux.provider.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @Column(unique = true, nullable = false)
    private long id;
    private String tipo_id;
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = true, unique = true)
    @JsonIgnore
    private UsuarioModel usuario;
    @OneToOne(fetch=FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "perfil_id", nullable = true, unique = true)
    private PerfilModel perfil;
    /*@OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "referente_id", nullable = false, unique = true)
    private ReferenteModel referente;*/

    @OneToMany(mappedBy = "proveedor")
    private List<ServicioModel> servicios;

    public ProveedorModel(long id, String tipo_id, UsuarioModel usuario, PerfilModel perfil) {
        this.id = id;
        this.tipo_id = tipo_id;
        this.usuario = usuario;
        this.perfil = perfil;
    }
}
