package com.nnk.springboot.utils;

import java.util.regex.Pattern;

public class IBANValidator {
    private static final String IBAN_REGEX = "^[A-Z]{2}[0-9]{2}[A-Z0-9]{1,30}$";

    public static boolean isValidIBAN(String iban) {
        if (iban == null || !Pattern.matches(IBAN_REGEX, iban)) {
            return false;
        }
        return validateChecksum(iban);
    }

    private static boolean validateChecksum(String iban) {
        // Réarangement déplacant la 4 premier character à la fin
        String reformattedIban = iban.substring(4) + iban.substring(0, 4);

        // Conversion en équivalent numérique
        StringBuilder numericIban = new StringBuilder();
        for (char c : reformattedIban.toCharArray()) {
            if (Character.isDigit(c)) {
                numericIban.append(c);
            } else {
                //converssion de la lettre en version numérique
                numericIban.append(c - 'A' + 10);
            }
        }

        // Modulo 97, validation de l'IBAN
        String ibanAsNumber = numericIban.toString();
        int remainder = 0;
        for (int i = 0; i < ibanAsNumber.length(); i++) {
            remainder = (remainder * 10 + (ibanAsNumber.charAt(i) - '0')) % 97;
        }

        // Si le resultat = 1, l'IBAN est validé
        return remainder == 1;
    }
}
