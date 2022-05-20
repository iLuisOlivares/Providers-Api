package com.aux.provider.models;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @Column(unique = true, nullable = false)
    private long id;
    @Column(unique = true, nullable = false)
    private String email;
    private String clave;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "usuario", fetch = FetchType.LAZY)
    private ProveedorModel proveedor;

    private boolean activo;

    public UsuarioModel(long id, String email, String clave, boolean activo) {
        this.id = id;
        this.email = email;
        this.clave = clave;
        this.activo = activo;
    }
}
