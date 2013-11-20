/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.awt.Frame;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author simon
 */
public class MiscTest {
    
    public MiscTest() {
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
     * Test of createErrorDialog method, of class Misc.
     */
    @Test
    public void testCreateErrorDialog_Frame_String() {
        System.out.println("createErrorDialog");
        Frame frame = null;
        String errors = "";
        boolean expResult = false;
        boolean result = Misc.createErrorDialog(frame, errors);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createErrorDialog method, of class Misc.
     */
    @Test
    public void testCreateErrorDialog_3args() {
        System.out.println("createErrorDialog");
        Frame frame = null;
        String errors = "";
        boolean singleError = false;
        boolean expResult = false;
        boolean result = Misc.createErrorDialog(frame, errors, singleError);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
