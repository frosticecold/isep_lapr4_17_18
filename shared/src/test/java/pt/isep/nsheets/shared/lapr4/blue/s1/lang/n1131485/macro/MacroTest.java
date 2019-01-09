package pt.isep.nsheets.shared.lapr4.blue.s1.lang.n1131485.macro;

import java.util.List;
import pt.isep.nsheets.shared.lapr4.blue.s1.lang.n1131485.macro.domain.Macro;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pt.isep.nsheets.shared.core.formula.lang.Language;
import pt.isep.nsheets.shared.core.formula.lang.LanguageManager;

/**
 *
 * @author Pedro Emanuel Coelho 1131485@isep.ipp.pt
 */
public class MacroTest {

    public MacroTest() {
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
     * Test of name method, of class Macro.
     */
    @Test
    public void ensureDefaultName() {
        System.out.println("name");
        Macro instance = new Macro("");
        String expResult = "MACRO001";
        String result = instance.name();
        assertEquals(expResult, result);
    }

    /**
     * Test of name method, of class Macro.
     */
    @Test
    public void ensureNameIsSet() {
        System.out.println("name");
        Macro instance = new Macro("MyMacro");
        String expResult = "MyMacro";
        String result = instance.name();
        assertEquals(expResult, result);
    }

    /**
     * Test of changeLanguage method, of class Macro.
     */
    @Test
    public void ensureDefaultLanguage() {
        System.out.println("changeLanguage");
        String lang = "";
        Macro instance = new Macro("");
        instance.changeLanguage(LanguageManager.getInstance().getLanguage("MacroExcel"));
        assertEquals("MacroExcel", instance.language().getName());

    }

    /**
     * Test of changeLanguage method, of class Macro.
     */
    @Test
    public void ensureChangeLanguageToJS() {
        System.out.println("changeLanguage");
        Macro instance = new Macro("");
        instance.changeLanguage(LanguageManager.getInstance().getLanguage("javas"));
        assertEquals("javas", instance.language().getName());

    }

    /**
     * Test of changeLanguage method, of class Macro.
     */
    @Test
    public void ensureChangeLanguageToVB() {
        System.out.println("changeLanguage");
        Macro instance = new Macro("");
        instance.changeLanguage(LanguageManager.getInstance().getLanguage("visualbasic"));
        assertEquals("visualbasic", instance.language().getName());
    }

    /**
     * Test of language method, of class Macro.
     */
    @Test
    public void testLanguage() {
        System.out.println("language");
        Macro instance = new Macro("Macro002");
        Language expResult = LanguageManager.getInstance().getLanguage("MacroExcel");
        Language result = instance.language();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Macro.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Macro instance = new Macro("");
        String expResult = "MACRO001 - MacroExcel language";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of add Commands method to ensure that they are saved
     */
    @Test
    public void ensureCommandsAreSaved() {

        System.out.println("enusureCommandsAreSaved()");
        Macro instance = new Macro("MACRO111");
        String commands = "|=A1:=3+7\n;Comment\n";
        instance.addCommand(commands);
        String result = instance.commands();
        assertEquals(commands,result);
    }
    
    /**
     * Test to ensure that macro gets reset, in other words the commands list is cleared
     */
    @Test
    public void ensureThatMacroGetsReset() {
        
        System.out.println("ensureThatMacroGetsReset()");
        Macro instance = new Macro("MACROTOGETRESET");
        String commands = "|=A1:=3+7\n;Comment\n";
        instance.addCommand(commands);
        String expResult = "";
        instance.resetMacro();
        String result = instance.commands();
        assertEquals(expResult,result);
    }
}
