package projektpc2;

import java.util.Map;

public class DatovyAnalytik extends Zamestnanec {

    public DatovyAnalytik(int id, String jmeno, String prijmeni, int rokNarozeni) {
        super(id, jmeno, prijmeni, rokNarozeni);
    }

    @Override
    public String getSkupina() {
        return "Datový analytik";
    }

    @Override
    public void provedDovednost(Map<Integer, Zamestnanec> vsichni) {
        int maxSpolecnych = -1;
        int idNejlepsiho = -1;

        for (Integer idKolegy : kolegove.keySet()) {
            Zamestnanec kolega = vsichni.get(idKolegy);
            if (kolega != null) {
                int spolecnych = 0;
                for (Integer idJehoKolegy : kolega.getKolegove().keySet()) {
                    if (this.kolegove.containsKey(idJehoKolegy)) {
                        spolecnych++;
                    }
                }
                if (spolecnych > maxSpolecnych) {
                    maxSpolecnych = spolecnych;
                    idNejlepsiho = idKolegy;
                }
            }
        }
        if (idNejlepsiho != -1) {
            System.out.println("Nejvíce společných kolegů (" + maxSpolecnych + ") mám s ID: " + idNejlepsiho);
        } else {
            System.out.println("Nemám dostatek dat pro analýzu.");
        }
    }
}
