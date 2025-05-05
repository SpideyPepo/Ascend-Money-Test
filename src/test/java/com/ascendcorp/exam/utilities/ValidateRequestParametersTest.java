package com.ascendcorp.exam.utilities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ValidateRequestParametersTest {

    private ValidateRequestParameters validator;
    private final Date validDate = new Date();

    @BeforeEach
    void setUp() {
        validator = new ValidateRequestParameters();
    }

    @Test
    void shouldThrowExceptionWhenTransactionIdIsNull() {
        assertThatThrownBy(() -> validator.validateRequest(null, validDate, "channel", "bankCode", "bankNumber", 100.0))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Transaction id is required!");
    }

    @Test
    void shouldThrowExceptionWhenTransactionDateIsNull() {
        assertThatThrownBy(() -> validator.validateRequest("txnId", null, "channel", "bankCode", "bankNumber", 100.0))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Transaction DateTime is required!");
    }

    @Test
    void shouldThrowExceptionWhenChannelIsNull() {
        assertThatThrownBy(() -> validator.validateRequest("txnId", validDate, null, "bankCode", "bankNumber", 100.0))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Channel is required!");
    }

    @Test
    void shouldThrowExceptionWhenBankCodeIsNullOrEmpty() {
        assertThatThrownBy(() -> validator.validateRequest("txnId", validDate, "channel", null, "bankNumber", 100.0))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Bank Code is required!");

        assertThatThrownBy(() -> validator.validateRequest("txnId", validDate, "channel", "", "bankNumber", 100.0))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Bank Code is required!");
    }

    @Test
    void shouldThrowExceptionWhenBankNumberIsNullOrEmpty() {
        assertThatThrownBy(() -> validator.validateRequest("txnId", validDate, "channel", "bankCode", null, 100.0))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Bank Number is required!");

        assertThatThrownBy(() -> validator.validateRequest("txnId", validDate, "channel", "bankCode", "", 100.0))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Bank Number is required!");
    }

    @Test
    void shouldThrowExceptionWhenAmountIsZeroOrNegative() {
        assertThatThrownBy(() -> validator.validateRequest("txnId", validDate, "channel", "bankCode", "bankNumber", 0))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Amount must be more than zero!");

        assertThatThrownBy(() -> validator.validateRequest("txnId", validDate, "channel", "bankCode", "bankNumber", -50))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Amount must be more than zero!");
    }

    @Test
    void shouldNotThrowExceptionWhenAllParametersAreValid() {
        validator.validateRequest("txnId", validDate, "channel", "bankCode", "bankNumber", 100.0);
    }
}