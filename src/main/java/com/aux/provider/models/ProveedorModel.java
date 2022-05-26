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
    private boolean activo;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "proveedor", fetch = FetchType.LAZY)
    @JsonManagedReference
    private UsuarioModel usuario;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "proveedor", fetch = FetchType.LAZY)
    @JsonManagedReference
    private PerfilModel perfil;

    @OneToMany(mappedBy = "proveedor")
    @JsonManagedReference
    private List<ServicioModel> servicios;

    public ProveedorModel(long id, String tipo_id) {
        this.id = id;
        this.tipo_id = tipo_id;
        this.activo = true;
    }
}
