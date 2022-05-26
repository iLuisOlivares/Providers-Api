package com.aux.provider.services.exceptions;

public class NoEncontradoException extends  Exception{
    //Exception cuando no se encuentra el recurso en la base de datos
    public NoEncontradoException(String msj){
        super(msj);
    }
}
