package com.util.base.b_class;

import java.util.Currency;
import java.util.Locale;

/**
 * @author y15079
 * @create: 9/22/17 1:27 AM
 * @desc:
 */
public class CurrencyExample {
    public static void main(String[] args) {
        Currency currency=Currency.getInstance(Locale.US);
        System.out.println("United States:"+currency.getSymbol());;

        currency=Currency.getInstance(Locale.UK);
        System.out.println("United Kingdom:"+currency.getSymbol());;

        currency=Currency.getInstance(Locale.FRANCE);
        System.out.println("France:"+currency.getSymbol());
    }
}
