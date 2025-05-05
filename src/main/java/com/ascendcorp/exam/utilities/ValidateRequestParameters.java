package com.ascendcorp.exam.utilities;

import java.util.Date;

public class ValidateRequestParameters {

    public void validateRequest(String transactionId, Date tranDateTime, String channel,
                                String bankCode, String bankNumber, double amount) {
        if (transactionId == null) throw new NullPointerException("Transaction id is required!");
        if (tranDateTime == null) throw new NullPointerException("Transaction DateTime is required!");
        if (channel == null) throw new NullPointerException("Channel is required!");
        if (bankCode == null || bankCode.isEmpty()) throw new NullPointerException("Bank Code is required!");
        if (bankNumber == null || bankNumber.isEmpty()) throw new NullPointerException("Bank Number is required!");
        if (amount <= 0) throw new NullPointerException("Amount must be more than zero!");
    }
}
