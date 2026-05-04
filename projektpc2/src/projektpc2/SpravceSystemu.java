package projektpc2;

import java.util.*;
import java.util.stream.Collectors;

public class SpravceSystemu {
    private Map<Integer, Zamestnanec> databaze = new HashMap<>();
    private int dalsiId = 1;

    public Map<Integer, Zamestnanec> getDatabaze() {
        return databaze;
    }

    public void nastavDatabazi(Map<Integer, Zamestnanec> novaDatabaze) {
        this.databaze = novaDatabaze;
        this.dalsiId = databaze.keySet().stream().max(Integer::compare).orElse(0) + 1;
    }

    public void pridejZamestnance(int typ, String jmeno, String prijmeni, int rok) {
        Zamestnanec z;
        if (typ == 1) {
            z = new DatovyAnalytik(dalsiId, jmeno, prijmeni, rok);
        } else {
            z = new BezpecnostniSpecialista(dalsiId, jmeno, prijmeni, rok);
        }
        databaze.put(dalsiId, z);
        System.out.println("Přidán: " + z.toString());
        dalsiId++;
    }

    public void pridejSpolupraci(int id1, int id2, UrovenSpoluprace uroven) {
        if (databaze.containsKey(id1) && databaze.containsKey(id2) && id1 != id2) {
            databaze.get(id1).pridejKolegu(id2, uroven);
            databaze.get(id2).pridejKolegu(id1, uroven);
            System.out.println("Spolupráce přidána.");
        } else {
            System.out.println("Chybné ID.");
        }
    }

    public void odeberZamestnance(int id) {
        if (databaze.containsKey(id)) {
            Zamestnanec z = databaze.get(id);
            for (Integer idKolegy : z.getKolegove().keySet()) {
                databaze.get(idKolegy).odeberKolegu(id);
            }
            databaze.remove(id);
            System.out.println("Zaměstnanec a jeho vazby odstraněny.");
        }
    }

    public void vyhledej(int id) {
        Zamestnanec z = databaze.get(id);
        if (z != null) {
            System.out.println(z);
            System.out.println("Počet vazeb: " + z.getKolegove().size());
        } else {
            System.out.println("Nenalezen.");
        }
    }

    public void spustDovednost(int id) {
        Zamestnanec z = databaze.get(id);
        if (z != null) {
            z.provedDovednost(databaze);
        }
    }

    public void vypisAbecedneVeSkupinach() {
        List<Zamestnanec> seznam = new ArrayList<>(databaze.values());
        seznam.sort(Comparator.comparing(Zamestnanec::getSkupina).thenComparing(Zamestnanec::getPrijmeni));
        for (Zamestnanec z : seznam) {
            System.out.println("ID: " + z.getId() + " | " + z.getSkupina() + " | " + z.getPrijmeni() + " " + z.getJmeno());
        }
    }

    public void statistiky() {
        int[] kvality = new int[3];
        Zamestnanec nejviceVazeb = null;

        for (Zamestnanec z : databaze.values()) {
            if (nejviceVazeb == null || z.getKolegove().size() > nejviceVazeb.getKolegove().size()) {
                nejviceVazeb = z;
            }
            for (UrovenSpoluprace u : z.getKolegove().values()) {
                kvality[u.ordinal()]++;
            }
        }

        System.out.println("Nejvíce vazeb má: " + (nejviceVazeb != null ? nejviceVazeb.getPrijmeni() : "Nikdo"));
        int maxIndex = 0;
        for (int i = 1; i < 3; i++) {
            if (kvality[i] > kvality[maxIndex]) maxIndex = i;
        }
        System.out.println("Převažující spolupráce: " + UrovenSpoluprace.values()[maxIndex]);
    }

    public void poctyVeSkupinach() {
        long analytici = databaze.values().stream().filter(z -> z instanceof DatovyAnalytik).count();
        long specialisti = databaze.values().stream().filter(z -> z instanceof BezpecnostniSpecialista).count();
        System.out.println("Datoví analytici: " + analytici);
        System.out.println("Bezpečnostní specialisté: " + specialisti);
    }
}