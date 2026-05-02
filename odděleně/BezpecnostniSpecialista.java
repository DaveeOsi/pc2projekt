package projektpc2;

import java.util.Map;

public class BezpecnostniSpecialista extends Zamestnanec {

    public BezpecnostniSpecialista(int id, String jmeno, String prijmeni, int rokNarozeni) {
        super(id, jmeno, prijmeni, rokNarozeni);
    }

    @Override
    public String getSkupina() {
        return "Bezpečnostní specialista";
    }

    @Override
    public void provedDovednost(Map<Integer, Zamestnanec> vsichni) {
        if (kolegove.isEmpty()) {
            System.out.println("Rizikové skóre: 0 (Žádní spolupracovníci)");
            return;
        }

        int sumaKvality = 0;
        for (UrovenSpoluprace uroven : kolegove.values()) {
            sumaKvality += uroven.ordinal() + 1; 
        }

        double prumer = (double) sumaKvality / kolegove.size();
        double rizikoveSkore = kolegove.size() / prumer;

        System.out.printf("Rizikové skóre spolupráce: %.2f%n", rizikoveSkore);
    }
}
