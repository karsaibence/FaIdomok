package modell;

public class Hasab extends Idom {

    private double a, b, m;

    public Hasab(double a, double b, double m) {
        this.a = a;
        this.b = b;
        this.m = m;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getM() {
        return m;
    }

    @Override
    public String toString() {
        return "Hasáb{" +
                "a: " + a +
                ", b: " + b +
                ", m: " + m +
                " alapterülete: " + alapTerulet() +
                " térfogata: " + terfogat() +
                " súlya: " + suly() +
                '}';
    }

    @Override
    public double alapTerulet() {
        return a * b;
        //teljes terület: (a*b*2)+4*(2*m*(a+b))
    }

    @Override
    public double suly() {
        return terfogat() * FAJSULY;
    }

    @Override
    public double terfogat() {
        return alapTerulet() * m;
    }
}
