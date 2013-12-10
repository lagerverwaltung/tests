/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package helper;

import com.j256.ormlite.dao.Dao;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Lager;
import model.Lagerbestand;
import model.Lagerfach;
import model.Teilebestand;
import model.Warenbewegung;
import model.ZielPosition;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author smodlich
 */

public class DatabaseManagerTest {
    
    private static DatabaseManager dbm;
            
    public DatabaseManagerTest() {
    }
    
    @BeforeClass
    public static void setup()
    {
        try {
            DatabaseManager.setTest(true);
             dbm= new DatabaseManager();
        } catch (SQLException ex) {
            fail("DB konnte nicht initalisiert werden!");
        }
    
    }
    
    @Test
    public void lagerIDTest()           
    {
        
        
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
     Dao<Lager,Integer> lagerDao= dbm.getLagerDao();
        int[] ids=new int[1];
        int j=0;
        try {
            List<Lager> lagerList = lagerDao.queryForAll();
            
             ids= new int[lagerList.size()];
            
            for(int i =0;i<lagerList.size();i++)
            {
                ids[i]=lagerList.get(i).getLagerID();
            }         
            j=ids.length;
        } catch (SQLException ex) {
            fail("Lager Query fehlgeschlagen!");
        }
        if(j==0 || j==1)
        {
            System.out.println("Menge der Lager:"+j);
            fail("Es wurden keine Ids zugewiesen! Oder nur eine!");
        }
        
        
        for(int k=0;k<j;k++)
        {
            if(k+1<j)
            assertFalse(ids[k]==ids[k+1]);
        }
     
    }
    
    @AfterClass
    public static void cleanDB()
    {
        try {
            Dao<Lager,Integer> lagerDao= dbm.getLagerDao();
            List<Lager> lagerList = lagerDao.queryForAll();
            lagerDao.delete(lagerList);
            
        } catch (SQLException ex) {
            System.out.println("Deletion Error!");
        }
    
    }

    
    
}
