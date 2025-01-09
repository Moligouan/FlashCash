package com.nnk.springboot.utils;

public class IBANFormatter {

    public static String formatIBAN(String iban) {
        return iban.replaceAll("(.{4})", "$1 ").trim();
    }

    public static String cleanIBAN(String iban) {
        return iban.replaceAll("\\s+", "");
    }
}
