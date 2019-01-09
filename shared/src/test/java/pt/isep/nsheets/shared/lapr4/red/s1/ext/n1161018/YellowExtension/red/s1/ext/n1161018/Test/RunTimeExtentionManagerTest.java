package pt.isep.nsheets.shared.lapr4.red.s1.ext.n1161018.YellowExtension.red.s1.ext.n1161018.Test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pt.isep.nsheets.shared.ext.ExtensionManager;
import pt.isep.nsheets.shared.ext.RunTimeExtentionManager;

import static org.junit.Assert.*;

/**
 * RunTimeExtentionManagerTest.java
 *
 * @autor: David Blanquett<1161018@isep.ipp.pt>
 * @date: 04/06/2018
 */
public class RunTimeExtentionManagerTest {

    ExtensionManager eManager;
    RunTimeExtentionManager runTime;
    String testColor = "Not a color";

    @Before
    public void setUp() throws Exception {

        eManager = ExtensionManager.getInstance();
        runTime = new RunTimeExtentionManager();
    }

    @After
    public void tearDown() throws Exception {
        eManager = null;
    }

    @Test
    public void currentYellowExtensionColor() {

        RunTimeExtentionManager test = new RunTimeExtentionManager();
        test.changeYellowExtensionColor(testColor);

        assertTrue(testColor.equals(test.currentYellowExtensionColor()));
    }

    @Test
    public void changeYellowExtensionColor() {


        runTime.changeYellowExtensionColor(testColor);

        assertTrue(testColor.equals(runTime.currentYellowExtensionColor()));
    }

}