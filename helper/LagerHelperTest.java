/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package helper;

import java.util.HashMap;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author smodlich
 */
public class LagerHelperTest {
    
    public LagerHelperTest() {
    }

    /**
     * Test of validateLagerData method, of class LagerHelper.
     * Testet die Lagervalidierung
     */
    @Test
    public void testValidateLagerData() {
        LagerHelper help = new LagerHelper();
        HashMap<Integer,String> errors;
        errors=help.validateLagerData("2", "3", "5","3","5","7");
        assertTrue(errors.isEmpty());
        errors.clear();
        errors=help.validateLagerData("-2", "3", "5","3","5","7");
        assertTrue(errors.containsKey(help.ALL_NOT_NEGATIVE_ID));
        errors.clear();
        errors=help.validateLagerData("2", "-3", "5","3","5","7");
        assertTrue(errors.containsKey(help.ALL_NOT_NEGATIVE_ID));
        errors.clear();
        errors=help.validateLagerData("2", "3", "-5","3","5","7");
        assertTrue(errors.containsKey(help.ALL_NOT_NEGATIVE_ID));
        errors.clear();
        errors=help.validateLagerData("2", "3", "5","-3","5","7");
        assertTrue(errors.containsKey(help.ALL_NOT_NEGATIVE_ID));
        errors.clear();
        errors=help.validateLagerData("2", "3", "5","3","-5","7");
        assertTrue(errors.containsKey(help.ALL_NOT_NEGATIVE_ID));
        errors.clear();
        errors=help.validateLagerData("-2", "3", "5","3","5","-7");
        assertTrue(errors.containsKey(help.ALL_NOT_NEGATIVE_ID));
        errors.clear();
        errors=help.validateLagerData("A", "3", "5","3","5","7");
        assertTrue(errors.containsKey(help.BREITE_NOT_INTEGER_ID));
        errors.clear();
        errors=help.validateLagerData("3", "B", "5","3","5","7");
        assertTrue(errors.containsKey(help.HOEHE_NOT_INTEGER_ID));
        errors.clear();
        errors=help.validateLagerData("3", "3", "CH","3","5","7");
        assertTrue(errors.containsKey(help.TIEFE_NOT_INTEGER_ID));
        errors.clear();
        errors=help.validateLagerData("3", "3", "5","dvde","5","7");
        assertTrue(errors.containsKey(help.FACHKLEIN_NOT_INTEGER_ID));
        errors.clear();
        errors=help.validateLagerData("3", "3", "5","3","sasdwdad","7");
        assertTrue(errors.containsKey(help.FACHMITTEL_NOT_INTEGER_ID));
        errors.clear();
        errors=help.validateLagerData("3", "3", "5","3","5","/&%");
        assertTrue(errors.containsKey(help.FACHGROSS_NOT_INTEGER_ID));
        errors.clear();
        
        errors=help.validateLagerData("3000", "3", "5","3","5","7");
        assertTrue(errors.containsKey(help.BREITE_MAX_VALUE_ID));
        errors.clear();
        errors=help.validateLagerData("3", "3233", "5","3","5","7");
        assertTrue(errors.containsKey(help.HOEHE_MAX_VALUE_ID));
        errors.clear();
        errors=help.validateLagerData("3", "3", "8750","3","5","7");
        assertTrue(errors.containsKey(help.TIEFE_MAX_VALUE_ID));
        errors.clear();  
        
        errors=help.validateLagerData("501", "5", "2","3","5","7");
        assertTrue(errors.containsKey(help.MAX_MATRIX_ID));
        errors.clear();
        
        errors=help.validateLagerData("0", "3", "5","3","5","7");
        assertTrue(errors.containsKey(help.ALL_ZERO_FORBIDDEN_ID));
        errors.clear();
        
        errors=help.validateLagerData("3", "0", "5","3","5","7");
        assertTrue(errors.containsKey(help.ALL_ZERO_FORBIDDEN_ID));
        errors.clear();
        
        errors=help.validateLagerData("3", "3", "0","3","5","7");
        assertTrue(errors.containsKey(help.ALL_ZERO_FORBIDDEN_ID));
        errors.clear();
        
        errors=help.validateLagerData("5", "3", "5","0","5","7");
        assertTrue(errors.containsKey(help.ALL_ZERO_FORBIDDEN_ID));
        errors.clear();
        
        errors=help.validateLagerData("3", "3", "5","3","0","7");
        assertTrue(errors.containsKey(help.ALL_ZERO_FORBIDDEN_ID));
        errors.clear();
        
        errors=help.validateLagerData("3", "3", "5","3","5","0");
        assertTrue(errors.containsKey(help.ALL_ZERO_FORBIDDEN_ID));
        errors.clear();
    }
    
}
