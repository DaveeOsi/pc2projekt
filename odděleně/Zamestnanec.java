package projektpc2;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public abstract class Zamestnanec implements Specializace, Serializable {
    private int id;
    private String jmeno;
    private String prijmeni;
    private int rokNarozeni;
    protected Map<Integer, UrovenSpoluprace> kolegove;

    public Zamestnanec(int id, String jmeno, String prijmeni, int rokNarozeni) {
        this.id = id;
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
        this.rokNarozeni = rokNarozeni;
        this.kolegove = new HashMap<>();
    }

    public int getId() { return id; }
    public String getJmeno() { return jmeno; }
    public String getPrijmeni() { return prijmeni; }
    public int getRokNarozeni() { return rokNarozeni; }
    public Map<Integer, UrovenSpoluprace> getKolegove() { return kolegove; }

    public void pridejKolegu(int idKolegy, UrovenSpoluprace uroven) {
        kolegove.put(idKolegy, uroven);
    }

    public void odeberKolegu(int idKolegy) {
        kolegove.remove(idKolegy);
    }

    public abstract String getSkupina();

    @Override
    public String toString() {
        return id + " | " + jmeno + " " + prijmeni + " (" + rokNarozeni + ") - " + getSkupina();
    }
}
