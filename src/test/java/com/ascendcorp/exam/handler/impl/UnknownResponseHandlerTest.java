package com.ascendcorp.exam.handler.impl;

import com.ascendcorp.exam.model.InquiryServiceResultDTO;
import com.ascendcorp.exam.model.TransferResponse;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UnknownResponseHandlerTest {

    @Test
    public void should_setReasonCodeAndReasonDesc_when_descriptionContainsCode() {
        UnknownResponseHandler handler = new UnknownResponseHandler();
        TransferResponse response = new TransferResponse();
        response.setResponseCode("unknown");
        response.setDescription("5001:Unknown error code 5001");

        InquiryServiceResultDTO resultDTO = new InquiryServiceResultDTO();
        handler.handleResponse(response, resultDTO);

        assertEquals("5001", resultDTO.getReasonCode());
        assertEquals("Unknown error code 5001", resultDTO.getReasonDesc());
    }

    @Test
    public void should_setGeneralInvalidData_when_descriptionIsNull() {
        UnknownResponseHandler handler = new UnknownResponseHandler();
        TransferResponse response = new TransferResponse();
        response.setResponseCode("unknown");

        InquiryServiceResultDTO resultDTO = new InquiryServiceResultDTO();
        handler.handleResponse(response, resultDTO);

        assertEquals("501", resultDTO.getReasonCode());
        assertEquals("General Invalid Data", resultDTO.getReasonDesc());
    }
}