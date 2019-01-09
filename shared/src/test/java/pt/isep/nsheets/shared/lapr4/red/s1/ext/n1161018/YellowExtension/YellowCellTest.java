package pt.isep.nsheets.shared.lapr4.red.s1.ext.n1161018.YellowExtension;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pt.isep.nsheets.shared.core.Cell;
import pt.isep.nsheets.shared.core.CellImpl;

import static org.junit.Assert.*;

/**
 * YellowCellTest.java
 *
 * @autor: David Blanquett<1161018@isep.ipp.pt>
 * @date: 04/06/2018
 */
public class YellowCellTest {

    Cell c;

    @Before
    public void setUp() throws Exception {

        c = new CellImpl();

    }

    @Test
    public void YellowCellConstructorTest(){
        System.out.println("Yellow Cell Contructor");


        YellowCell yC = new YellowCell(c);

        assertEquals(c, yC.getDelegate());


    }


    @After
    public void tearDown() throws Exception {
        c= null;
    }
}