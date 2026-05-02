package projektpc2;

import java.io.*;
import java.util.Map;

public class SouborovyManazer {
    private static final String SOUBOR = "zamestnanec_export.dat";

    public void ulozZamestnance(Zamestnanec z) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SOUBOR))) {
            oos.writeObject(z);
            System.out.println("Zaměstnanec uložen do souboru.");
        } catch (IOException e) {
            System.out.println("Chyba při ukládání: " + e.getMessage());
        }
    }

    public Zamestnanec nactiZamestnance() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SOUBOR))) {
            System.out.println("Zaměstnanec načten.");
            return (Zamestnanec) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Chyba při načítání: " + e.getMessage());
            return null;
        }
    }
}
