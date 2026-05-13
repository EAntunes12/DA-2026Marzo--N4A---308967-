package da.ort.obligatorioErnestoAntunes.modelo;

public enum Estado {
    DEFINIDA(0),
    ABIERTA(1),
    ESTABLE(2),
    CERRADA(3),
    FINALIZADA(4);

    private final int valor;

    Estado(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}
