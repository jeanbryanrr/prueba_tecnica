package com.prueba.app.model;

public class ErrorApp {

    private String mensaje;

    public ErrorApp(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
