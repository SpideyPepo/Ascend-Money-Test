package com.ascendcorp.exam.handler.impl;

import com.ascendcorp.exam.model.InquiryServiceResultDTO;
import com.ascendcorp.exam.model.TransferResponse;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InvalidDataResponseHandlerTest {

    @Test
    public void should_setReasonCodeAndReasonDesc_when_descriptionContainsCode() {
        InvalidDataResponseHandler handler = new InvalidDataResponseHandler();
        TransferResponse response = new TransferResponse();
        response.setResponseCode("invalid_data");
        response.setDescription("100:1091:Data type is invalid.");

        InquiryServiceResultDTO resultDTO = new InquiryServiceResultDTO();
        handler.handleResponse(response, resultDTO);

        assertEquals("1091", resultDTO.getReasonCode());
        assertEquals("Data type is invalid.", resultDTO.getReasonDesc());
    }

    @Test
    public void should_setGeneralInvalidData_when_descriptionIsNull() {
        InvalidDataResponseHandler handler = new InvalidDataResponseHandler();
        TransferResponse response = new TransferResponse();
        response.setResponseCode("invalid_data");

        InquiryServiceResultDTO resultDTO = new InquiryServiceResultDTO();
        handler.handleResponse(response, resultDTO);

        assertEquals("400", resultDTO.getReasonCode());
        assertEquals("General Invalid Data", resultDTO.getReasonDesc());
    }
}