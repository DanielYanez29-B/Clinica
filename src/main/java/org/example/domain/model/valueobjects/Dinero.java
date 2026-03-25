package org.example.domain.model.valueobjects;

import java.math.BigDecimal;

public class Dinero {
    private final BigDecimal monto;

    public Dinero(BigDecimal monto) {
        if (monto == null || monto.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El monto no puede ser nulo o negativo");
        }
        this.monto = monto;
    }

    public static Dinero de(double cantidad) {
        return new Dinero(BigDecimal.valueOf(cantidad));
    }

    public BigDecimal getMonto() {
        return monto;
    }

}
