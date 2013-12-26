/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.SQLException;
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
    
    public TeilebestandTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
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
       Teilebestand teil = new Teilebestand();
       teil.setBezeichnung(bez);
       teil.setMaterialgruppe(matGr);
       teil.setPreis(preis);
       teil.setTyp(Teilebestand.Typ.kaufteile);
       teil.setZeichnungsnummer(zeichNr);
       teil.setVe(ve);
       try {
           teil.save();
       }
       catch (SQLException ex){
           fail("Save failed");
       }
        assertEquals(teil.getBezeichnung(), bez);
        assertEquals(teil.getMaterialgruppe(), matGr);
        assertEquals(teil.getPreis(), preis, 0.1);
        assertNotNull(preis);
        assertEquals(teil.getTyp(), Teilebestand.Typ.kaufteile);
        assertEquals(teil.getVe(), ve);
        assertNotNull(ve);
        assertEquals(teil.getZeichnungsnummer(), zeichNr);
        
       
    }

}