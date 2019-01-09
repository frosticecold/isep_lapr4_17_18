package pt.isep.nsheets.client.application.Lapr.Red.n1161018.ext;

import gwt.material.design.client.constants.Color;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pt.isep.nsheets.shared.core.*;
import pt.isep.nsheets.shared.lapr4.green.s3.core.n1161294.ImageExtension;

import static org.junit.Assert.*;

/**
 * UIControllerTest.java
 *
 * @autor: David Blanquett<1161018@isep.ipp.pt>
 * @date: 04/06/2018
 */
public class UIControllerTest {

    UIController uiController;
    Color redColor;

    @Before
    public void setUp() throws Exception {

        uiController = new UIController();
        redColor = Color.RED;
    }

    @After
    public void tearDown() throws Exception {

        uiController = null;
    }

    @Test
    public void changeYellowExtensionColor() {

        uiController.changeYellowExtensionColor(redColor.name());

        assertTrue(uiController.showCurrentExtensionColor().toString().equals("RED"));


    }

    @Test
    public void showCurrentExtensionColor() {

        uiController.changeYellowExtensionColor(redColor.name());

        assertTrue(uiController.showCurrentExtensionColor().equals(redColor));
    }
}