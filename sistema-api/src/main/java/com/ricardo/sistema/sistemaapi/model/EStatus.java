package com.ricardo.sistema.sistemaapi.model;

public enum EStatus {
    PENDENTE,
    FAZENDO,
    COMPLETADO;

    public EStatus proximo() {
        EStatus status = this;
        int index = ordinal() + 1;

        // Verifica se o índice é menor que o número total de status
        if (index < values().length) {
            status = values()[index];
        } else {
            // Retorna o primeiro status quando o último é alcançado
            status = values()[0];
        }

        return status;
    }
}
