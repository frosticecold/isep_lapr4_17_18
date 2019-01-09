package pt.isep.nsheets.shared.lapr4.green.s2.lang.n1161294.Money;

public enum Currency {

    EURO {
        @Override
        public String toString() {
            return "e";
        }
    } ,
    DOLLAR{
        @Override
        public String toString() {
            return "$";
        }
    } ,
    POUND{
        @Override
        public String toString() {
            return "£";
        }
    };

    /**
     * Given an string, transforms it to a currency object
     *
     * @param currency string to convert
     * @return currency object or throws exception if not found
     */
    public static Currency toCurrency(String currency){

        if(currency.equals("e") || currency.toLowerCase().equals("euro"))
            return EURO;
        else if(currency.equals("$") || currency.toLowerCase().equals("dollar"))
            return DOLLAR;
        else if(currency.equals("£") || currency.toLowerCase().equals("pound"))
            return POUND;
        else
            throw new IllegalArgumentException("Not such currency found: " + currency);
    }
}
