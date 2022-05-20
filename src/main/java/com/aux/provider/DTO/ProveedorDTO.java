package com.aux.provider.DTO;

import com.aux.provider.models.ServicioModel;
import lombok.Data;

import java.util.List;
@Data
public class ProveedorDTO {
    private long id;
    private String tipo_id;
    private List<ServicioModel> servicios;

}
