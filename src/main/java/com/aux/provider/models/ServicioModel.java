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
@Table(name = "servicio")
public class ServicioModel {
    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(unique = true, nullable = false)
    private long id;
    private String titulo;
    private String area_servicio;
    private String servicio_especifico;
    private String descripcion;
    private long precio;
    private boolean activo;
    @ManyToOne
    @JoinColumn(name="proveedor_id", nullable=false)
    @JsonBackReference
    private ProveedorModel proveedor;

    public boolean isActivo() {
        return activo;
    }
    public void setServicio(String titulo, String area_servicio, String servicio_especifico, String descripcion, long precio) {
        this.titulo = titulo;
        this.area_servicio = area_servicio;
        this.servicio_especifico = servicio_especifico;
        this.descripcion = descripcion;
        this.precio = precio;
    }

}
