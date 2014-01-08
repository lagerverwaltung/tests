/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import com.j256.ormlite.dao.Dao;
import java.sql.SQLException;
import java.util.List;
import model.Lager;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author smodlich
 */
public class LagerInitalisierenDBTest {

    private static DatabaseManager dbm;

    public LagerInitalisierenDBTest() {
    }

    /**
     * Setup Methode zur Einrichtung der DB.
     */
    @BeforeClass
    public static void setup() {
        try {
            DatabaseManager.setTest(true);
            dbm = new DatabaseManager();
        } catch (SQLException ex) {
            fail("DB konnte nicht initalisiert werden!");
        }

    }

    /**
     * Testet Werte Zuweisung, und liest die Werte wieder aus.
     */
    @Test
    public void wertZuweisung() {
        int breite = 4;
        int hoehe = 5;
        int tiefe = 7;
        int kleinVE = 4;
        int mittelVE = 6;
        int grossVE = 6;
        Lager lfrei = new Lager();
        lfrei.setBreite(breite);
        lfrei.setHoehe(hoehe);
        lfrei.setTiefe(tiefe);
        lfrei.setKleinVE(kleinVE);
        lfrei.setMittelVE(mittelVE);
        lfrei.setGrossVE(grossVE);
        lfrei.setLagerort(Lager.Lagerort.freilager);
        try {
            lfrei.save();
        } catch (SQLException ex) {
            fail("Save Fail!");
        }
        assertEquals(lfrei.getBreite(), breite);
        assertEquals(lfrei.getHoehe(), hoehe);
        assertEquals(lfrei.getTiefe(), tiefe);
        assertEquals(lfrei.getKleinVE(), kleinVE);
        assertEquals(lfrei.getMittelVE(), mittelVE);
        assertEquals(lfrei.getGrossVE(), grossVE);
        assertEquals(lfrei.getLagerort(), Lager.Lagerort.freilager);

    }

    /**
     * Initalisiert beide Lager und gibt ihnen die Lagerorte, anschließend Test
     * ob Lagerorte korrekt gespeichert wurden
     */
    @Test
    public void lagerortTest() {
        Lager frei = new Lager();
        frei.setLagerort(Lager.Lagerort.freilager);
        Lager hoch = new Lager();
        hoch.setLagerort(Lager.Lagerort.hochregal);
        try {
            frei.save();
            hoch.save();
        } catch (SQLException ex) {
            fail("Save failed.");
        }

        assertEquals(frei.getLagerort(), Lager.Lagerort.freilager);
        assertEquals(hoch.getLagerort(), Lager.Lagerort.hochregal);


    }

    /**
     * Testet ob beiden Lagern eine einzigartige ID zugewiesen wird.
     */
    @Test
    public void lagerIDTest() {


        Lager lfrei = new Lager();
        lfrei.setBreite(2);
        lfrei.setHoehe(2);
        lfrei.setTiefe(2);
        lfrei.setKleinVE(2);
        lfrei.setMittelVE(4);
        lfrei.setGrossVE(6);
        lfrei.setLagerort(Lager.Lagerort.freilager);

        Lager lhoch = new Lager();
        lhoch.setBreite(2);
        lhoch.setHoehe(2);
        lhoch.setTiefe(2);
        lhoch.setKleinVE(2);
        lhoch.setMittelVE(4);
        lhoch.setGrossVE(6);
        lhoch.setLagerort(Lager.Lagerort.hochregal);

        try {
            lfrei.save();
            lhoch.save();
        } catch (SQLException ex) {
            fail("Lager konnte nicht initalisiert werden!");
        }
        Dao<Lager, Integer> lagerDao = dbm.getLagerDao();
        int[] ids = new int[1];
        int j = 0;
        try {
            List<Lager> lagerList = lagerDao.queryForAll();

            ids = new int[lagerList.size()];

            for (int i = 0; i < lagerList.size(); i++) {
                ids[i] = lagerList.get(i).getLagerID();
            }
            j = ids.length;
        } catch (SQLException ex) {
            fail("Lager Query fehlgeschlagen!");
        }
        if (j == 0 || j == 1) {
            System.out.println("Menge der Lager:" + j);
            fail("Es wurden keine Ids zugewiesen! Oder nur eine!");
        }


        for (int k = 0; k < j; k++) {
            if (k + 1 < j) {
                assertFalse(ids[k] == ids[k + 1]);
            }
        }

    }

    @After
    public void cleanDB() {
        try {
            Dao<Lager, Integer> lagerDao = dbm.getLagerDao();
            List<Lager> lagerList = lagerDao.queryForAll();
            lagerDao.delete(lagerList);

        } catch (SQLException ex) {
            System.out.println("Deletion Error!");
        }
        DatabaseManager.setTest(false);

    }
}
