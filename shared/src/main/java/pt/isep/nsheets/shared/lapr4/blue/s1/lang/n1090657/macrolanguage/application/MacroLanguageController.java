package pt.isep.nsheets.shared.lapr4.blue.s1.lang.n1090657.macrolanguage.application;

import java.util.List;

import pt.isep.nsheets.shared.core.formula.lang.LanguageManager;
import pt.isep.nsheets.shared.lapr4.blue.s1.lang.n1090657.shared.lang.MacroLanguage;

public class MacroLanguageController {

    public MacroLanguageController() {

    }

    public List<MacroLanguage> getAllMacroLanguages() {
        return LanguageManager.getInstance().getAllMacroLanguages();

    }
}
