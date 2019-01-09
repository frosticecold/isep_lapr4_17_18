package pt.isep.nsheets.shared.lapr4.blue.s1.lang.n1131485.macro.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import pt.isep.nsheets.shared.core.formula.lang.Language;
import pt.isep.nsheets.shared.core.formula.lang.LanguageManager;

/**
 * @author Pedro Emanuel Coelho
 */
public class Macro implements Serializable {

    private static final String MACRO_DEFAULTNAME = "MACRO001";
    private static final long serialVersionUID = -555904204300434608L;

    public String getName() {
        return name;
    }

    /**
     * Name of current macro
     */
    private String name;

    /**
     * Language of current macro
     */
    private transient Language language;

    /**
     * list of lines of current macro
     */
    private String commandList; //each position is a line of the macro, being a comment or a formula

    public Macro(String givenName) {
        this.validateName(givenName);
        this.language = LanguageManager.getInstance().getLanguage("MacroExcel");
        this.commandList = "";
    }

    private Long id;

    public Macro() {
    }

    /**
     * quick validate of the name of the macro
     *
     * @param givenName
     */
    private void validateName(String givenName) {

        if (givenName.isEmpty() || givenName.equals(null)) {
            this.name = MACRO_DEFAULTNAME;
        } else {
            this.name = givenName;
        }
    }



    /**
     * Returns name of Macro
     */
    public String name() {

        return this.name;
    }

    /**
     * Change language
     */
    public void changeLanguage(Language lang) {

        if (!this.language.equals(lang)) {
            this.language = lang;
        }

    }

    /**
     * Returns language of Macro
     */
    public Language language() {

        return this.language;
    }

    /**
     * Adds a new command to the list of current macro
     *
     * @param command
     */
    public void addCommand(String command) {

        this.commandList = command;
    }

    /**
     * Returns this macros commands
     */
    public String commands() {

        return this.commandList;
    }

    /**
     * Clears all the commands on the macro
     */
    public void resetMacro() {

        this.commandList = "";
    }

    @Override
    public String toString() {

        return this.name() + " - " + this.language().getName() + " language";
    }

}
