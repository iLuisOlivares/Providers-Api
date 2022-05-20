package com.aux.provider.DTO;

import com.aux.provider.models.ProveedorModel;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
@Data
public class UsuarioDTO {
    private long id;
    private String email;
    private String clave;
    private boolean activo;

}
