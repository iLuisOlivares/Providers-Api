package com.aux.provider.services.interfaces;

import com.aux.provider.models.ServicioModel;
import com.aux.provider.services.exceptions.NoEncontradoException;

import java.util.ArrayList;
import java.util.List;

public interface ServicioServiceInterface {
    ArrayList<ServicioModel> getServicios();

    ServicioModel getServicio(Long id) throws NoEncontradoException;

    List<ServicioModel> getServiciosByProveedor(Long id);

    List<ServicioModel> getServiciosProv(Long id);


    ServicioModel updateServicio(ServicioModel servicio, Long id) throws NoEncontradoException;

}
