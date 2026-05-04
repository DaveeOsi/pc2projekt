package projektpc2;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class SqlManazer {
    private static final String URL = "jdbc:sqlite:firma.db";

    public void vytvorTabulku() {
        String sql = "CREATE TABLE IF NOT EXISTS zamestnanci (id INTEGER PRIMARY KEY, jmeno TEXT, prijmeni TEXT, rok INTEGER, typ TEXT)";
        try (Connection conn = DriverManager.getConnection(URL); Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("SQL Chyba: " + e.getMessage());
        }
    }

    public void ulozVse(Map<Integer, Zamestnanec> databaze) {
        vytvorTabulku();
        try (Connection conn = DriverManager.getConnection(URL)) {
            Statement stmt = conn.createStatement();
            stmt.execute("DELETE FROM zamestnanci"); 

            String insert = "INSERT INTO zamestnanci (id, jmeno, prijmeni, rok, typ) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(insert);

            for (Zamestnanec z : databaze.values()) {
                pstmt.setInt(1, z.getId());
                pstmt.setString(2, z.getJmeno());
                pstmt.setString(3, z.getPrijmeni());
                pstmt.setInt(4, z.getRokNarozeni());
                pstmt.setString(5, z.getSkupina());
                pstmt.executeUpdate();
            }
            System.out.println("Záloha do SQL úspěšná.");
        } catch (SQLException e) {
            System.out.println("Chyba SQL zápisu: " + e.getMessage());
        }
    }

    public Map<Integer, Zamestnanec> nactiVse() {
        Map<Integer, Zamestnanec> mapa = new HashMap<>();
        vytvorTabulku();
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM zamestnanci")) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String jm = rs.getString("jmeno");
                String pr = rs.getString("prijmeni");
                int rok = rs.getInt("rok");
                String typ = rs.getString("typ");

                Zamestnanec z = typ.equals("Datový analytik") ? 
                    new DatovyAnalytik(id, jm, pr, rok) : 
                    new BezpecnostniSpecialista(id, jm, pr, rok);
                mapa.put(id, z);
            }
            System.out.println("Data z SQL úspěšně načtena.");
        } catch (SQLException e) {
            System.out.println("Data z SQL nenalezena nebo chyba.");
        }
        return mapa;
    }
}
