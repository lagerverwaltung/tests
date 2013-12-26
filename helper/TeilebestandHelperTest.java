/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.sql.SQLException;
import java.util.HashMap;
import javax.swing.JTable;
import model.Teilebestand;
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
public class TeilebestandHelperTest {
    
    public TeilebestandHelperTest() {
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
     * Test of getInstance method, of class TeilebestandHelper.
     
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        TeilebestandHelper expResult = null;
        TeilebestandHelper result = TeilebestandHelper.getInstance();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    } */

    /**
     * Test of validateTeilData method, of class TeilebestandHelper.
     */
 /*   @Test
    public void testValidateTeilData() {
        System.out.println("validateTeilData");
        String bez = "";
        String mat = "";
        String zeich = "";
        String groes = "";
        String pri = "";
        TeilebestandHelper instance = new TeilebestandHelper();
        HashMap expResult = null;
        HashMap result = instance.validateTeilData(bez, mat, zeich, groes, pri);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/
    

    /**
     * Test of refreshTeileTableModel method, of class TeilebestandHelper.
     */
   /* @Test
    public void testRefreshTeileTableModel() {
        System.out.println("refreshTeileTableModel");
        JTable teileBestandTable = null;
        TeilebestandHelper.refreshTeileTableModel(teileBestandTable);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");    }
*/
}