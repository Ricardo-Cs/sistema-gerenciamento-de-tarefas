package com.ricardo.sistema.sistemaapi.model;

public enum EStatus {
    PENDENTE,
    FAZENDO,
    COMPLETADO;

    public EStatus proximo() {
    EStatus status = this;
    int index = ordinal() +1;
    if (index < values().length) {
        status = values()[index];
    }
    return status;
}
}
