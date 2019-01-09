package pt.isep.nsheets.shared.lapr4.green.s2.lang.n1161294.Money;

import org.junit.Test;

import static org.junit.Assert.*;

public class MoneyTest {

    @Test
    public void compareTo() {
        Money m1 = new Money(20, Currency.DOLLAR);
        Money m2 = new Money(20, Currency.EURO);
        Money m3 = new Money(20, Currency.POUND);

        assertTrue( m1.compareTo(m2) < 0);
        assertTrue( m1.compareTo(m3) < 0);
        assertTrue( m2.compareTo(m3) < 0);
    }

    @Test
    public void currentCurrency() {
        Money m1 = new Money(20, Currency.DOLLAR);
        Money m2 = new Money(20, Currency.EURO);
        Money m3 = new Money(20, Currency.POUND);

        assertEquals(2000, m1.amount);
        assertEquals(2000, m2.amount);
        assertEquals(2000, m3.amount);
    }

    @Test
    public void updateMoney() {
        Money m1 = new Money(20, Currency.EURO);
        assertEquals(Currency.EURO, m1.currentCurrency());
        assertEquals(2000, m1.amount);

        m1.updateMoney(1, Currency.DOLLAR);

        assertEquals(Currency.DOLLAR, m1.currentCurrency());
        assertEquals(2000, m1.amount);
    }

    @Test
    public void addMoney() {
        Money m1 = new Money(20, Currency.DOLLAR);
        Money m2 = new Money(20, Currency.DOLLAR);

        assertEquals(4000, m1.addMoney(m2).amount);

        Money m3 = new Money(20, Currency.DOLLAR);

        assertEquals(6000, m1.addMoney(m3).amount);
    }

    @Test
    public void divideMoney() {
        Money m1 = new Money(20, Currency.DOLLAR);

        assertEquals(1000, m1.divideMoney(2).amount);

        assertEquals(500, m1.divideMoney(2).amount);
    }

    @Test
    public void multiplyMoney() {
        Money m1 = new Money(20, Currency.DOLLAR);

        assertEquals(4000, m1.multiplyMoney(2).amount);

        assertEquals(8000, m1.multiplyMoney(2).amount);
    }

    @Test
    public void subtractMoney() {
        Money m1 = new Money(60, Currency.DOLLAR);
        Money m2 = new Money(20, Currency.DOLLAR);

        assertEquals(4000, m1.subtractMoney(m2).amount);

        Money m3 = new Money(40, Currency.DOLLAR);

        assertEquals(0, m1.subtractMoney(m3).amount);
    }

    @Test
    public void sameCurrency() {
        Money m1 = new Money(60, Currency.DOLLAR);
        Money m2 = new Money(20, Currency.DOLLAR);
        Money m3 = new Money(20, Currency.EURO);

        assertEquals(true, m1.sameCurrency(m2));
        assertEquals(false, m1.sameCurrency(m3));
    }
}