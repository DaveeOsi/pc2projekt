package projektpc2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SpravceSystemu spravce = new SpravceSystemu();
        SqlManazer sql = new SqlManazer();
        SouborovyManazer soubor = new SouborovyManazer();
        Scanner sc = new Scanner(System.in);

        
        spravce.nastavDatabazi(sql.nactiVse());

        boolean bezi = true;
        while (bezi) {
            System.out.println("\n1. Přidat zaměstnance\n2. Přidat spolupráci\n3. Odebrat zaměstnance\n4. Vyhledat\n5. Dovednost\n6. Výpis abecedně\n7. Statistiky\n8. Počty ve skupinách\n9. Uložit do souboru\n10. Ukončit (SQL záloha)");
            System.out.print("Volba: ");
            int volba = sc.nextInt();

            switch (volba) {
                case 1:
                    System.out.print("Skupina (1=Analytik, 2=Specialista): ");
                    int typ = sc.nextInt();
                    System.out.print("Jméno: "); String jm = sc.next();
                    System.out.print("Příjmení: "); String pr = sc.next();
                    System.out.print("Rok: "); int rok = sc.nextInt();
                    spravce.pridejZamestnance(typ, jm, pr, rok);
                    break;
                case 2:
                    System.out.print("ID prvního: "); int id1 = sc.nextInt();
                    System.out.print("ID druhého: "); int id2 = sc.nextInt();
                    System.out.print("Úroveň (0=Špatná, 1=Průměrná, 2=Dobrá): ");
                    UrovenSpoluprace u = UrovenSpoluprace.values()[sc.nextInt()];
                    spravce.pridejSpolupraci(id1, id2, u);
                    break;
                case 3:
                    System.out.print("ID: "); spravce.odeberZamestnance(sc.nextInt());
                    break;
                case 4:
                    System.out.print("ID: "); spravce.vyhledej(sc.nextInt());
                    break;
                case 5:
                    System.out.print("ID: "); spravce.spustDovednost(sc.nextInt());
                    break;
                case 6: spravce.vypisAbecedneVeSkupinach(); break;
                case 7: spravce.statistiky(); break;
                case 8: spravce.poctyVeSkupinach(); break;
                case 9:
                    System.out.print("ID k uložení: ");
                    Zamestnanec z = spravce.getDatabaze().get(sc.nextInt());
                    if (z != null) soubor.ulozZamestnance(z);
                    break;
                case 10:
                    sql.ulozVse(spravce.getDatabaze());
                    bezi = false;
                    break;
            }
        }
    }
}