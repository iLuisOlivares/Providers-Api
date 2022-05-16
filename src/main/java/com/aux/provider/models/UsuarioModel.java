package com.aux.provider.models;
import javax.persistence.*;

@Entity
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
    private ProveedorModel proveedorModel;

    private boolean activo;

    public UsuarioModel(Long id, String email, String clave, boolean activo) {
        this.id = id;
        this.email = email;
        this.clave = clave;
        this.activo = activo;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getClave() {
        return clave;
    }

    public String getEmail() {
        return email;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    public ProveedorModel getProveedorModel() {
        return proveedorModel;
    }

    public void setProveedorModel(ProveedorModel proveedorModel) {
        this.proveedorModel = proveedorModel;
    }
}
