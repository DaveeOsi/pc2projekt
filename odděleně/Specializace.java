package projektpc2;

import java.util.Map;

public interface Specializace {
    // Přidali jsme do závorky parametr, aby se shodoval s analytikem a specialistou
    void provedDovednost(Map<Integer, Zamestnanec> vsichni);
}
