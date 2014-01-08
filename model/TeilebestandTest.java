/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.j256.ormlite.dao.Dao;
import helper.DatabaseManager;
import helper.Misc;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Artjom
 */
public class TeilebestandTest {
    
    private static DatabaseManager dbm;
    
    public TeilebestandTest() {
    }
    
    
    @AfterClass
    public static void tearDownClass() {
    }
    
   @BeforeClass
    public static void setup() {
        try {
            DatabaseManager.setTest(true);
            dbm = new DatabaseManager();
        } catch (SQLException ex) {
            fail("DB konnte nicht initalisiert werden!");
        }

    }
    
    @After
    public void tearDown() {
        
    }

     /**
     * Initalisiert ein Teil und gibt ihnen ein Teil zurück 
     */
    @Test
    public void TeilebestandTypTest(){
    Teilebestand kauft= new Teilebestand();
    kauft.setTyp(Teilebestand.Typ.kaufteile);
    Teilebestand vorrat= new Teilebestand();
    vorrat.setTyp(Teilebestand.Typ.vorratsteile);
    Teilebestand vorricht = new Teilebestand();
    vorricht.setTyp(Teilebestand.Typ.vorrichtungen);
    Teilebestand unfertBaugr = new Teilebestand();
    unfertBaugr.setTyp(Teilebestand.Typ.unfertigeBaugruppen);
    Teilebestand werkz = new Teilebestand();
    werkz.setTyp(Teilebestand.Typ.werkzeuge);
    try {
        kauft.save();
        vorrat.save();
        werkz.save();
        vorricht.save();
        unfertBaugr.save();
    }
    catch (SQLException ex){
        fail("Save failed");
    }
    } 
     /**
     * Testet Werte Zuweisung, und liest die Werte wieder aus.
     */
    
    @Test
    public void WertZuweisung (){
       String bez = "Test";
       String matGr = "Test";
       String zeichNr = "Test";
       float preis = 3;
       int ve = 1;
        try {
            Teilebestand teil = new Teilebestand();
            teil.setBezeichnung(bez);
            teil.setMaterialgruppe(matGr);
            teil.setPreis(preis);
            teil.setTyp(Teilebestand.Typ.kaufteile);
            teil.setZeichnungsnummer(zeichNr);
            teil.setVe(ve);
      
           teil.save();
       
        assertEquals(teil.getBezeichnung(), bez);
        assertEquals(teil.getMaterialgruppe(), matGr);
        assertEquals(teil.getPreis(), preis, 0.1);
        assertNotNull(preis);
        assertEquals(teil.getTyp(), Teilebestand.Typ.kaufteile);
        assertEquals(teil.getVe(), ve);
        assertNotNull(ve);
        assertEquals(teil.getZeichnungsnummer(), zeichNr);
        
        } catch (SQLException ex) {
            fail(ex.getSQLState());
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