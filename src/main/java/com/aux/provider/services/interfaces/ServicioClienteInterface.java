package com.aux.provider.services.interfaces;

public interface ServicioClienteInterface {

    void sendEmail(String from, String to, String subject, String content);
}
