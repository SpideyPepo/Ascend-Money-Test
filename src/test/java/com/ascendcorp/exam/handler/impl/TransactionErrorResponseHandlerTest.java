package com.ascendcorp.exam.handler.impl;

import com.ascendcorp.exam.model.InquiryServiceResultDTO;
import com.ascendcorp.exam.model.TransferResponse;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TransactionErrorResponseHandlerTest {

    @Test
    public void should_setReasonCodeAndReasonDesc_when_descriptionContainsCode() {
        TransactionErrorResponseHandler handler = new TransactionErrorResponseHandler();
        TransferResponse response = new TransferResponse();
        response.setResponseCode("transaction_error");
        response.setDescription("100:1091:Transaction is error with code 1091.");

        InquiryServiceResultDTO resultDTO = new InquiryServiceResultDTO();
        handler.handleResponse(response, resultDTO);

        assertEquals("1091", resultDTO.getReasonCode());
        assertEquals("Transaction is error with code 1091.", resultDTO.getReasonDesc());
    }

    @Test
    public void should_setGeneralTransactionError_when_descriptionIsNull() {
        TransactionErrorResponseHandler handler = new TransactionErrorResponseHandler();
        TransferResponse response = new TransferResponse();
        response.setResponseCode("transaction_error");

        InquiryServiceResultDTO resultDTO = new InquiryServiceResultDTO();
        handler.handleResponse(response, resultDTO);

        assertEquals("500", resultDTO.getReasonCode());
        assertEquals("General Transaction Error", resultDTO.getReasonDesc());
    }
}