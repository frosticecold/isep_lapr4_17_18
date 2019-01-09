package pt.isep.nsheets.shared.core.formula.lang;

import java.util.ArrayList;

import java.util.List;

import pt.isep.nsheets.shared.core.formula.Function;
import pt.isep.nsheets.shared.lapr4.blue.s1.lang.n1090657.visualbasic.language.VisualBasicLanguage;
import pt.isep.nsheets.shared.lapr4.blue.s1.lang.n1151708.formula.lang.JavascriptLanguage;
import pt.isep.nsheets.shared.lapr4.blue.s1.lang.n1090657.shared.lang.MacroLanguage;
import pt.isep.nsheets.shared.lapr4.blue.s1.lang.n1131485.macroExcel.language.MacroExcelLanguage;
import pt.isep.nsheets.shared.lapr4.green.s2.lang.n1161294.language.MonetaryLanguage;

public class LanguageManager {

	/**
	 * The singleton instance
	 */
	private static final LanguageManager instance = new LanguageManager();

	/**
	 * The languages
	 */
	private List<Language> languages = new ArrayList<Language>();

	/**
	 * Creates a new language.
	 */
	private LanguageManager() {
		// setup all languages

		languages.add(new ExcelLanguage("excel","="));
		languages.add(new JavascriptLanguage("javas",""));
		languages.add(new VisualBasicLanguage("visualbasic",""));
        languages.add(new MacroExcelLanguage("MacroExcel",""));
		languages.add(new MonetaryLanguage("monetary", "#"));
	}

	/**
	 * Returns the singleton instance.
	 *
	 * @return the singleton instance
	 */
	public static LanguageManager getInstance() {
		return instance;
	}

	public Language getLanguage(String name) {
		for (Language lang : languages) {
			if (name == lang.getName()) {
				return lang;
			}
		}
		return null;
	}

	public Function getFunction(String identifier) throws UnknownElementException {
		for (Language lang : languages) {
			try {
				Function function = lang.getFunction(identifier);
				return function;
			} catch (UnknownElementException e) {
				// nothing to do, move to next language
			}
		}
		// funtion was not found in any of the languages
		throw new UnknownElementException(identifier);
	}

	/**
	 * Method to get all Macro Languages
	 * 
	 * @return
	 */
	public List<MacroLanguage> getAllMacroLanguages() {
		List<MacroLanguage> list = new ArrayList<>();
		languages.stream().filter((lang) -> (lang instanceof MacroLanguage)).forEachOrdered((lang) -> {
			
			list.add((MacroLanguage)lang);
		});
		return list;
	}
        
        public List<Language> getLanguages() {
            List<Language> ll=new ArrayList<Language>();
		for (Language lang : languages) {
			ll.add(lang);
			}
		
		return ll;
	}
        
        
}
