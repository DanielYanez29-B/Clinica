package org.example.domain.model;

import org.example.domain.model.valueobjects.Dinero;

public enum Especialidad {
    MEDICO_GENERAL(30, Dinero.de(500.0)),
    CARDIOLOGO(45, Dinero.de(900.0)),
    PSICOLOGA(60, Dinero.de(700.0));

    private final int duracioMinutos;
    private final Dinero costoBase;

    Especialidad(int duracioMinutos, Dinero costoBase) {
        this.duracioMinutos = duracioMinutos;
        this.costoBase = costoBase;
    }

    public int getDuracionMinutos() {
        return duracioMinutos;
    }
    public Dinero getCostoBase() {
        return costoBase;
    }

}
