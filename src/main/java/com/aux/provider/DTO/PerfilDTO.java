package com.aux.provider.DTO;

import lombok.Data;

@Data
public class PerfilDTO {
    private long id;
    private String nombre;
    private String apellidos;
    private String direccion;
    private long celular;
    private String foto;
    private String descripcion;
    private String pagina_web;
}
