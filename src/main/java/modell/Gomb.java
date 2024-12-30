package modell;

public class Gomb extends Idom {
    private double sugar;

    public Gomb(double sugar) {
        this.sugar = sugar;
    }

    public double getSugar() {
        return sugar;
    }

    @Override
    public String toString() {
        return "Gömb{" +
                "sugár: " + sugar +
                " felszíne: " + alapTerulet() +
                " térfogata: " + terfogat() +
                " súlya: " + suly() +
                '}';
    }

    @Override
    public double alapTerulet() {
        return Math.pow(sugar,2)*Math.PI*4;
    }

    @Override
    public double suly() {
        return terfogat()*FAJSULY;
    }

    @Override
    public double terfogat() {
        return (4*Math.pow(sugar,3)*Math.PI)/3;
    }
}
