package pt.isep.nsheets.shared.lapr4.green.s2.lang.n1161294.Money;

import pt.isep.nsheets.shared.core.IllegalValueTypeException;

import java.io.Serializable;


public class Money implements Comparable<Money>, Serializable {

    /**
     * Amount of value (always multiplied by 100)
     */
    protected long amount;

    /**
     * Current currency
     */
    private Currency currency;

    private static final int HUNDRED = 100;

    /**
     * ORM
     */
    private Money(){}

    /**
     * Constructor
     *
     * @param amount amount of value
     * @param currency current currency
     */
    public Money(double amount, Currency currency){
        if(currency == null)
            throw new NullPointerException("Argument currency is null!");

        this.amount = (int) (amount * HUNDRED);
        this.currency = currency;
    }

    /**
     * Compare two object of type money
     *
     * @param money money to compare
     * @return value
     */
    @Override
    public int compareTo(Money money) {
        Integer res = 0;

        try {
            res = (int) (CurrencyConverterService.convertToEuro(this).toMoney().amount
                    - CurrencyConverterService.convertToEuro(money).toMoney().amount);
        } catch (IllegalValueTypeException e) {
            e.printStackTrace();
        }

        return res;
    }

    /**
     * Devolves current currency
     *
     * @return currency
     */
    protected Currency currentCurrency(){
        return currency;
    }

    /**
     * Update money to a new currency
     *
     * @param rate rate for the conversion
     * @param currency new currency
     * @return money with new currency and new value
     */
    public Money updateMoney(double rate, Currency currency){
        this.amount *= rate;
        this.currency = currency;

        return this;
    }

    /**
     * Add money
     *
     * @param toAdd money to add
     * @return money with sum
     */
    public Money addMoney(Money toAdd){
        if(!sameCurrency(toAdd))
            return null;

        this.amount += toAdd.amount;
        return this;
    }

    /**
     * Divide money
     *
     * @param todivide value for division
     * @return new money with division
     */
    public Money divideMoney(double todivide){
        this.amount /= todivide;
        return this;
    }

    /**
     * Multiply money
     *
     * @param toMultiply value for multiplication
     * @return money after multiplication
     */
    public Money multiplyMoney(double toMultiply){
        this.amount *= toMultiply;
        return this;
    }

    /**
     * Subtract money
     * @param toSubtract money to subtract
     * @return money after subtraction
     */
    public Money subtractMoney(Money toSubtract){
        if(!sameCurrency(toSubtract))
            return null;


        this.amount -= toSubtract.amount;
        return this;
    }

    /**
     * Verifies if two money objects have the same currency
     *
     * @param money second money object
     * @return true if two money objects have the same currency
     */
    public boolean sameCurrency(Money money){
        return currency.equals(money.currency);
    }

    /**
     * Show information of money
     *
     * @return information
     */
    @Override
    public String toString() {
        return ((double) amount / HUNDRED) + currency.toString();
    }
}
