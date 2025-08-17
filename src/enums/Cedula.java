package src.enums;

public enum Cedula {
    CEM(100),
    CINQUENTA(50),
    VINTE(20),
    DEZ(10),
    CINCO(5),
    DOIS(2);

    private final int valor;

    Cedula(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}
