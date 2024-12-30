package controller;

import modell.Gomb;
import modell.Hasab;
import modell.Idom;

import java.util.*;

public class Engine {
    ArrayList<Idom> idomok = new ArrayList<>();
    Scanner sc;

    public Engine() {
        this.idomok = new ArrayList<>();
    }

    public double idomokOsszSuly() {
        double osszegzes = 0;
        for (Idom idom : idomok) {
            if (idom instanceof Gomb g) {
                osszegzes += g.suly();
            } else if (idom instanceof Hasab h) {
                osszegzes += h.suly();
            }
        }
        return osszegzes;
    }

    public double gombokOsszSuly() {
        double osszegzes = 0;
        for (Idom idom : idomok) {
            if (idom instanceof Gomb g) {
                osszegzes += g.suly();
            }
        }
        return osszegzes;
    }

    public Idom[] getKisNagyIdom() {
        Idom[] i = new Idom[2];
        i[0] = idomok.getFirst();
        i[1] = idomok.getFirst();
        for (Idom e : idomok) {
            if (e instanceof Gomb) {
                if (e.terfogat() < i[0].terfogat()) {
                    i[0] = e;
                } else if (e.terfogat() > i[1].terfogat()) {
                    i[1] = e;
                }
            } else if (e instanceof Hasab) {
                if (e.terfogat() < i[0].terfogat()) {
                    i[0] = e;
                } else if (e.terfogat() > i[1].terfogat()) {
                    i[1] = e;
                }
            }
        }

        return i;
    }

    private int idomValaszto() {
        valszOpcioszoveg();

        sc = new Scanner(System.in);
        int felhasznaloValasza = 0;
        try {
            felhasznaloValasza = sc.nextInt();
            if (felhasznaloValasza == 1 || felhasznaloValasza == 2) {
                idomKeszit(felhasznaloValasza);
                return felhasznaloValasza;
            } else if (felhasznaloValasza == 100 && idomok.size() >= 2) {
                Idom[] i = getKisNagyIdom();
                for (Idom idom : idomok) {
                    System.out.println(idom);
                }
                System.out.println("A idomok össz súlya: " + idomokOsszSuly());
                System.out.println("A gömbök össz súlya: " + gombokOsszSuly());
                valaszto();
                System.out.println("A legkissebb térfogatú idom: " + i[0] +
                        "\nA legnagyobb térfogatú idom: " + i[1]);
                valaszto();
                System.out.println(kulonbozoMeretuGombok().size() + " darab különböző sugarú gömb van, amelyek: " + kulonbozoMeretuGombok());
                valaszto();

                for (Map.Entry<Double, Integer> doubleIntegerEntry : azonosTeruletuHasabMenny().entrySet()) {
                    System.out.println("T:"+doubleIntegerEntry.getKey()+" -> "+doubleIntegerEntry.getValue()+" db");
                }
            } else {
                System.out.println("Nem jó számot adtál meg.");
            }
        } catch (InputMismatchException error) {
            System.out.println("Nem számot adtál meg");
        }
        return felhasznaloValasza;
    }

    private void valaszto() {
        System.out.println("------------------------------------------------");
    }

    private HashSet kulonbozoMeretuGombok() {
        HashSet<Double> gomb = new HashSet<>();
        for (Idom idom : idomok) {
            if (idom instanceof Gomb g) {
                gomb.add(g.getSugar());
            }
        }
        return gomb;
    }

    private HashMap<Double,Integer>  azonosTeruletuHasabMenny() {
        HashMap<Double, Integer> alapTer = new HashMap<>();
        for (Idom idom : idomok) {
            if (idom instanceof Hasab) {
                double terulet = idom.alapTerulet();
                if (!alapTer.containsKey(terulet)) {
                    alapTer.put(terulet, 1);
                } else {
                    alapTer.put(terulet, alapTer.get(terulet) + 1);
                }
            }
        }
        return alapTer;
    }

    private void valszOpcioszoveg() {
        //kell-e a 4.
        if (idomok.size() < 2) {
            System.out.println("Add meg milyen idomot szeretnél:");
            System.out.println("1. Gömb.");
            System.out.println("2. Hasáb.");
        } else {
            System.out.println("Add meg milyen idomot szeretnél:");
            System.out.println("1. Gömb.");
            System.out.println("2. Hasáb.");
            System.out.println("100. Ha nem szeretnél több idomot, akkor írd be a '100'-at.");
        }
    }

    private void idomKeszit(int beerkezo) {

        if (beerkezo == 1) {
            double sugar = getAdatok("sugara");

            idomok.add(new Gomb(sugar));
        } else if (beerkezo == 2) {
            double a = getAdatok("a oldala");
            double b = getAdatok("b oldala");
            double magassag = getAdatok("magassága");
            idomok.add(new Hasab(a, b, magassag));
        }
    }

    private double getAdatok(String szo) {
        System.out.print("Az idom " + szo + ": ");
        double kimenoErtek = sc.nextDouble();

        while (!isHelyesAdat(kimenoErtek)) {
            System.out.print("Nem lehet kisebb vagy egyenlő mint 0. Az idom " + szo + ": ");
            kimenoErtek = sc.nextDouble();
        }
        return kimenoErtek;
    }

    private boolean isHelyesAdat(double adat) {
        return adat > 0;
    }

    //nincs kész
    public void run() {
        int valasz = 0;
        while (idomok.size() < 2 || valasz != 100) {
            valasz = idomValaszto();
        }
    }

}
