package com.mediquei.config.bandeiras;

public class VisaCreditCard {

    public static boolean isBrandVisa(final String number) {
        return number != null && number.matches("4[0-9]{15}");}
}
