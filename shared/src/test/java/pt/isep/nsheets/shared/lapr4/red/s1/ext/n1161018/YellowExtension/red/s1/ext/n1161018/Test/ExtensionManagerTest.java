package pt.isep.nsheets.shared.lapr4.red.s1.ext.n1161018.YellowExtension.red.s1.ext.n1161018.Test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pt.isep.nsheets.shared.ext.Extension;
import pt.isep.nsheets.shared.ext.ExtensionManager;
import pt.isep.nsheets.shared.ext.RunTimeExtentionManager;
import pt.isep.nsheets.shared.lapr4.red.s1.ext.n1161018.YellowExtension.YellowExtension;

import static org.junit.Assert.*;

/**
 * ExtensionManagerTest.java
 *
 * @autor: David Blanquett<1161018@isep.ipp.pt>
 * @date: 04/06/2018
 */
public class ExtensionManagerTest {

    ExtensionManager eManager;
    RunTimeExtentionManager runTime;

    @Before
    public void setUp() throws Exception {

        eManager = ExtensionManager.getInstance();
        runTime = eManager.getRunTimeSettings();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getInstance() {

        assertTrue(eManager.equals(ExtensionManager.getInstance()));
    }

    @Test
    public void getExtensions() {

        Extension ex[] = eManager.getExtensions();

        assertTrue(ex.length > 0);
    }

    @Test
    public void getExtension() {

        Extension yellowEx = eManager.getExtension("Yellow");

        assertFalse(yellowEx!=null);
    }

    @Test
    public void addExtension() {
        Extension yE = new YellowExtension();

        assertTrue(eManager.addExtension(yE));

    }

    @Test
    public void getRunTimeSettings() {


        assertEquals(runTime, eManager.getRunTimeSettings());
    }
}