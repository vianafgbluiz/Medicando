package com.mediquei.config;

import com.mediquei.config.bandeiras.AmexCreditCard;
import com.mediquei.config.bandeiras.DinersCreditCard;
import com.mediquei.config.bandeiras.EloCreditCard;
import com.mediquei.config.bandeiras.HipercardCreditCard;
import com.mediquei.config.bandeiras.MasterCreditCard;
import com.mediquei.config.bandeiras.VisaCreditCard;

public class CreditCard {

    private String creditCard;

    public CreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public int getBrand() {
        if (!validateNumber()) {
            return 0;
        }

        this.creditCard = creditCard.replaceAll("[.\\s]", "");
        // As bandeiras são caraterizadas por conjuntos de BINs com sobre-posição.
        // Identificar a bandeira correta por verificar primeiro a bandeira com BINs mais específicos.

        if (AmexCreditCard.isBrandAmex(creditCard)) {
            return 1;
        }
        if (DinersCreditCard.isBrandDiners(creditCard)) {
            return 2;
        }
        if (EloCreditCard.isBrandElo(creditCard)) {
            return 3;
        }
        if (HipercardCreditCard.isBrandHipercard(creditCard)) {
            return 4;
        }
        if (MasterCreditCard.isBrandMaster(creditCard)) {
            return 5;
        }
        if (VisaCreditCard.isBrandVisa(creditCard)) {
            return 6;
        }
        return 0;
    }

    public boolean validateNumber() {
        return creditCard != null && creditCard.replaceAll("[.\\s]", "").matches("\\d{13,19}");
    }

    public boolean isValid() {
        return 0 != getBrand();
    }
}