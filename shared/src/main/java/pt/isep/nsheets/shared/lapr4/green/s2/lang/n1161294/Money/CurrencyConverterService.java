package pt.isep.nsheets.shared.lapr4.green.s2.lang.n1161294.Money;

import pt.isep.nsheets.shared.core.Value;

public class CurrencyConverterService {

    private static final double EURO_TO_DOLLAR = 1.18;
    private static final double EURO_TO_POUND = 0.88;

    private static final double DOLLAR_TO_EURO = 0.85;
    private static final double DOLLAR_TO_POUND = 0.74;

    private static final double POUND_TO_EURO = 1.14;
    private static final double POUND_TO_DOLLAR = 1.34;


    /**
     * Converts money to euro
     *
     * @param toConvert money to convert
     * @return Value with money and its new currency
     */
    public static Value convertToEuro(Money toConvert){

        switch (toConvert.currentCurrency()){
            case EURO : return new Value(toConvert.updateMoney(1, Currency.EURO));

            case POUND: return new Value(toConvert.updateMoney(POUND_TO_EURO, Currency.EURO));

            case DOLLAR: return new Value(toConvert.updateMoney(DOLLAR_TO_EURO, Currency.EURO));

            default: throw new IllegalArgumentException("Not such currency found: " + toConvert.currentCurrency());
        }

    }

    /**
     * Converts money to dollar
     *
     * @param toConvert money to convert
     * @return Value with money and its new currency
     */
    private static Value convertToDollar(Money toConvert){

        switch (toConvert.currentCurrency()){
            case EURO : return new Value(toConvert.updateMoney(EURO_TO_DOLLAR, Currency.DOLLAR));

            case POUND: return new Value(toConvert.updateMoney(POUND_TO_DOLLAR, Currency.DOLLAR));

            case DOLLAR: return new Value(toConvert.updateMoney(1, Currency.DOLLAR));

            default: throw new IllegalArgumentException("Not such currency found: " + toConvert.currentCurrency());
        }

    }

    /**
     * Converts money to pound
     *
     * @param toConvert money to convert
     * @return Value with money and its new currency
     */
    private static Value convertToPound(Money toConvert){

        switch (toConvert.currentCurrency()){
            case EURO : return new Value(toConvert.updateMoney(EURO_TO_POUND, Currency.POUND));

            case POUND: return new Value(toConvert.updateMoney(1, Currency.POUND));

            case DOLLAR: return new Value(toConvert.updateMoney(DOLLAR_TO_POUND, Currency.POUND));

            default: throw new IllegalArgumentException("Not such currency found: " + toConvert.currentCurrency());
        }

    }

    /**
     * Converts money to given currency
     *
     * @param toConvert money to convert
     * @param currency currency to convert money
     * @return Value with money and its new currency
     */
    public static Value convertToCurrency(Money toConvert, Currency currency){
        switch (currency){
            case EURO: return convertToEuro(toConvert);

            case DOLLAR: return convertToDollar(toConvert);

            case POUND: return convertToPound(toConvert);

            default: throw new IllegalArgumentException("Not such currency found: " + toConvert.currentCurrency());
        }
    }
}
