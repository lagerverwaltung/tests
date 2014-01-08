/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import com.j256.ormlite.dao.Dao;
import helper.DatabaseManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Lager;
import model.Lagerbestand;
import model.Lagerfach;
import model.Teilebestand;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author smodlich
 */
public class BestandsaenderungFrameTest {
    
    private static DatabaseManager dbm;
    
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
     * Testet die Einlagerung aus dem Teilebestand komplett von außen
     * Integrationstest
     */
    @Test
    public void testBestandsaenderung()
    {
        Teilebestand teil = new Teilebestand();
        teil.setBezeichnung("TestTeil");
        teil.setIdentnummer(1);
        teil.setMaterialgruppe("MG");
        teil.setPreis(20);
        teil.setVe(1);
        teil.setZeichnungsnummer("3E");
        
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
        
        
        try {
            BestandsaenderungFrame frame = new BestandsaenderungFrame(BestandsaenderungFrame.EINLAGERN_TEILEBESTAND,teil.getIdentnummer(),0);
            frame.setVisible(false);
            teil.save();
            frame.bestandsGuiHelper.setTeilID(teil.getIdentnummer());
           
            DateFormat d= new SimpleDateFormat("dd.MM.yyyy");
            Date hbDatum=null;
            
            try {
                hbDatum = d.parse("10.10.2014");
            } catch (ParseException ex) {
                fail("Datum parsen fehlgeschlagen");
            }
            frame.bestandsGuiHelper.setHbDatum(hbDatum);
            List<Lagerfach> list =lfrei.getFaecher();
            frame.bestandsGuiHelper.setQuellFachID(list.get(1).getFachnummer());
            Lagerfach[]  faecher= new Lagerfach[list.size()];
            for (int i=0;i<list.size();i++)
            {
                faecher[i]=list.get(i);
            
            }
            frame.bestandsGuiHelper.setFaecher(faecher);
            ArrayList<HashMap> al = new ArrayList<HashMap>();
            HashMap hm = new HashMap();
            hm.put("fachCode","FL");
            int x=1;
            hm.put("x",""+x);
            int y=1;
            hm.put("y",""+y);
            int z=1;
            hm.put("z",""+z);
            int menge=1;
            hm.put("qty",""+menge);
            al.add(hm);
            frame.bestandsGuiHelper.setDestinations(al);
             frame.bestandsGuiHelper.setMenge(menge);
            frame.bestandsAenderung(BestandsaenderungFrame.EINLAGERN_TEILEBESTAND, frame.bestandsGuiHelper);
            
           Lagerbestand l= Lagerbestand.getLagerbestand(teil.getIdentnummer(), list.get(0).getFachnummer());
           assertEquals(menge,l.getMenge());
           assertEquals(l.getLagerfach().getX(),x);
           assertEquals(l.getLagerfach().getY(),y);
           assertEquals(l.getLagerfach().getZ(),z);
           
        } catch (SQLException ex) {
            fail(ex.getSQLState());
        }
        
        try {
            Thread.sleep(290000000);
        } catch (InterruptedException ex) {
            Logger.getLogger(BestandsaenderungFrameTest.class.getName()).log(Level.SEVERE, null, ex);
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
