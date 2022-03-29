package Extra;

public enum TipoCuenta {

    // Enum que representa los tipos de cuenta
    NORMAL(0.02, 500), VIP(0.04, 1000), PLANILLA(0, 200);

    private double tasaInteres;
    private double minSaldo;

    TipoCuenta(double tasaInteres, double miniSaldo) {
        this.tasaInteres = tasaInteres;
        this.minSaldo = miniSaldo;
    }

    public double getTasaInteres() {
        return tasaInteres;
    }

    public double getMinSaldo() {
        return minSaldo;
    }
}
