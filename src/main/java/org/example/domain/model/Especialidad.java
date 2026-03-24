package org.example.domain.model;

public enum Especialidad {
    MEDICO_GENERAL(30, 0),
    CARDIOLOGO(45, 0),
    PSICOLOGA(50, 10);

    private final int minutosConsulta;
    private final int minutosDescanso;

    Especialidad(int minutosConsulta, int minutosDescanso) {
        this.minutosConsulta = minutosConsulta;
        this.minutosDescanso = minutosDescanso;
    }

    public int getDuracionMinutos() {
        return minutosConsulta + minutosDescanso;
    }
}
