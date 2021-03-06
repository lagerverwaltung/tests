/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package helper;

import helper.BestandsGUIHelper;
import com.j256.ormlite.dao.Dao;
import helper.DatabaseManager;
import java.sql.SQLException;
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
import view.BestandsaenderungFrame;

/**
 *
 * @author smodlich
 */
public class BestandsGUIHelperTest {
    private static DatabaseManager dbm;
    
    public BestandsGUIHelperTest()
    {   }
    
    @BeforeClass
    public static void setup() {
        try {
            DatabaseManager.setTest(true);
            dbm = new DatabaseManager();
        } catch (SQLException ex) {
            fail("DB konnte nicht initalisiert werden!");
        }
    }

    
    public void testKapazitaetsTest()
    {
        BestandsGUIHelper help = new BestandsGUIHelper();
        
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
            List<Lagerfach> list= lfrei.getFaecher();
            Lagerfach fach = list.get(0);
            Teilebestand tb= new Teilebestand();
            tb.setBezeichnung("Teil");
            tb.setIdentnummer(1);
            tb.setMaterialgruppe("MG");
            tb.setPreis(2);
            tb.setTyp(Teilebestand.Typ.kaufteile);
            tb.setVe(2);
            tb.setZeichnungsnummer("Z");
            tb.save();
           
          HashMap<Integer,String> errors=  help.kapazitaetsTest(fach, tb, 2, 0);
            
            if (!errors.isEmpty())
                fail("Kapizitätstest Fehler");
            
        } catch (SQLException ex) {
            System.out.println(ex.getSQLState());
        }
        
       
    }
    @Test
    public void validateLagerbestandDataTest() 
    {
    BestandsGUIHelper help = new BestandsGUIHelper();
    HashMap<Integer,String> errors = new HashMap<Integer,String>();
    
    try{
    errors=help.validateLagerbestandData(1, "10", "10.10.2014", "Grund", new ArrayList<HashMap>());
    assertTrue(errors.isEmpty());
    errors.clear();
    errors=help.validateLagerbestandData(1, "10", "10.10.2012", "Grund", new ArrayList<HashMap>());
    assertTrue(errors.containsKey(help.DATE_BEFORE_TODAY));
    errors.clear();
    errors=help.validateLagerbestandData(1, "-1", "10.10.2014", "Grund", new ArrayList<HashMap>());
    assertTrue( errors.containsKey(help.MENGE_NOT_GREATER_ZERO));
    errors.clear();
    errors=help.validateLagerbestandData(1, "E1", "10.10.2014", "Grund", new ArrayList<HashMap>());
    assertTrue(errors.containsKey(help.MENGE_NOT_INTEGER));
    errors.clear();
    errors=help.validateLagerbestandData(1, "10", "10.1", "Grund", new ArrayList<HashMap>());
    assertTrue(errors.containsKey(help.DATE_NOT_VALID));
    errors.clear();
    errors=help.validateLagerbestandData(1, "10", "10.10.2014", null, new ArrayList<HashMap>());
    assertTrue(errors.containsKey(help.NO_GRUND));
    errors.clear();
    
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
    
    Lagerbestand quellLb= new Lagerbestand();
    Teilebestand tb= new Teilebestand();
    tb.setBezeichnung("Teil");
    tb.setIdentnummer(1);
    tb.setMaterialgruppe("MG");
    tb.setPreis(2);
    tb.setTyp(Teilebestand.Typ.kaufteile);
    tb.setVe(2);
    tb.setZeichnungsnummer("Z");
    tb.save();
    quellLb.setTeil(tb);
    quellLb.setLagerfach(Lagerfach.getFach(lfrei, 1, 1, 1));
    quellLb.setMenge(5);
    quellLb.setLagerbestandsnummer(2);
    quellLb.setAnschaffungsgrund("AG");
    quellLb.save();
    
    help.setQuellFachID(quellLb.getLagerfach().getFachnummer());
    help.setTeilID(quellLb.getTeil().getIdentnummer());
    
    errors=help.validateLagerbestandData(BestandsaenderungFrame.AUSLAGERN, "7","10.10.2014" ,"Grund" , new ArrayList<HashMap>());
    assertTrue(errors.containsKey(help.AUSLAGERN_TOHIGH_ID));
    errors.clear();
    
    errors=help.validateLagerbestandData(BestandsaenderungFrame.AUSLAGERN, "3","10.10.2014" ,"Grund" , new ArrayList<HashMap>());
    assertFalse(errors.containsKey(help.AUSLAGERN_TOHIGH_ID));
    errors.clear();
    
    }
    catch(SQLException ex)
    {   
        fail(ex.toString());
    }
    assertTrue(errors.isEmpty());
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


