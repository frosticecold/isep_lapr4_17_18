package pt.isep.nsheets.client.application.Lapr.Red.n1161018.Search;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * OptionTypeTest.java
 *
 * @autor: David Blanquett<1161018@isep.ipp.pt>
 * @date: 10/06/2018
 */
public class OptionTypeTest {

    /* TYPE: CHANGE,DONT_CHANGE,UNDEFINED; */

    OptionType change;
    OptionType dntChange;
    OptionType und;


    @Before
    public void setUp() throws Exception {

        change = OptionType.CHANGE;
        dntChange = OptionType.DONT_CHANGE;
        und = OptionType.UNDEFINED;
    }


    @Test
    public void sameType() {
        System.out.println("SAMETYPE_TEST");


        assertTrue(OptionType.sameType(change,change));
        assertTrue(OptionType.sameType(dntChange,dntChange));
        assertTrue(OptionType.sameType(und,und));


        assertFalse(OptionType.sameType(change,dntChange));
        assertFalse(OptionType.sameType(change,und));
        assertFalse(OptionType.sameType(und, dntChange));

    }
}