package com.ascendcorp.exam.handler.impl;

import com.ascendcorp.exam.model.InquiryServiceResultDTO;
import com.ascendcorp.exam.model.TransferResponse;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ApprovedResponseHandlerTest {

    @Test
    public void should_setReasonCode200_and_setDescriptionAsAccountName() {
        ApprovedResponseHandler handler = new ApprovedResponseHandler();
        TransferResponse response = new TransferResponse();
        response.setResponseCode("approved");
        response.setDescription("approved");

        InquiryServiceResultDTO resultDTO = new InquiryServiceResultDTO();
        handler.handleResponse(response, resultDTO);

        assertEquals("200", resultDTO.getReasonCode());
        assertEquals("approved", resultDTO.getReasonDesc());
        assertEquals("approved", resultDTO.getAccountName());
    }
}