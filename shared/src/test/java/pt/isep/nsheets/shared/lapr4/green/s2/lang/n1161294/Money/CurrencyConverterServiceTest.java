package pt.isep.nsheets.shared.lapr4.green.s2.lang.n1161294.Money;

import org.junit.Test;
import pt.isep.nsheets.shared.core.IllegalValueTypeException;

import static org.junit.Assert.*;

public class CurrencyConverterServiceTest {

    @Test
    public void convertToCurrency() {
        Money m1 = new Money(1, Currency.DOLLAR);
        Money m2 = new Money(1, Currency.EURO);
        Money m3 = new Money(1, Currency.POUND);

        try {
            assertEquals(85, CurrencyConverterService.convertToCurrency(m1, Currency.EURO).toMoney().amount, 1);
            m1 = new Money(1, Currency.DOLLAR);
            assertEquals(74, CurrencyConverterService.convertToCurrency(m1, Currency.POUND).toMoney().amount, 1);

            assertEquals(118, CurrencyConverterService.convertToCurrency(m2, Currency.DOLLAR).toMoney().amount, 1);
            m2 = new Money(1, Currency.EURO);
            assertEquals(88, CurrencyConverterService.convertToCurrency(m2, Currency.POUND).toMoney().amount, 1);

            assertEquals(114, CurrencyConverterService.convertToCurrency(m3, Currency.EURO).toMoney().amount, 1);
            m3 = new Money(1, Currency.POUND);
            assertEquals(134, CurrencyConverterService.convertToCurrency(m3, Currency.DOLLAR).toMoney().amount, 1);

        } catch (IllegalValueTypeException e) {
            fail();
        }

    }
}