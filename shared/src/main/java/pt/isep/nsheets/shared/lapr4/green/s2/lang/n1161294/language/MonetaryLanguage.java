package pt.isep.nsheets.shared.lapr4.green.s2.lang.n1161294.language;

import pt.isep.nsheets.shared.core.formula.lang.Language;

public class MonetaryLanguage extends Language {

    /**
     * Constructor
     *
     * @param name name of language
     * @param starter starter string
     */
    public MonetaryLanguage(String name,String starter) {
        super(name,starter);
    }

    /**
     * Initialize binary operations of monetary language
     */
    @Override
    protected void initBinaryOperators() {
        binaryOperators.add(new AdderMonetary());
        binaryOperators.add(new DividerMonetary());
        binaryOperators.add(new MultiplierMonetary());
        binaryOperators.add(new SubtracterMonetary());
    }

    /**
     * Devolves the monetary language
     * @return monetary information
     */
    @Override
    public String toString() {
        return "monetary";
    }
}
