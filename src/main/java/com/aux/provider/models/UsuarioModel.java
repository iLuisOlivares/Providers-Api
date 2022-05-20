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
@Table(name = "usuario")
public class UsuarioModel {
    @Id
    @GeneratedValue(strategy = AUTO)

    @Column(unique = true, nullable = false)
    private long id;
    @Column(unique = true, nullable = false)
    private String email;
    private String clave;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "proveedor_id", nullable = true, unique = true)
    @JsonBackReference
    private ProveedorModel proveedor;



    public UsuarioModel(long id, String email, String clave) {
        this.id = id;
        this.email = email;
        this.clave = clave;
    }
}
