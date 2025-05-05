package com.ascendcorp.exam.handler.impl;

import com.ascendcorp.exam.model.InquiryServiceResultDTO;
import com.ascendcorp.exam.model.TransferResponse;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DefaultResponseHandlerTest {

    @Test
    public void should_setInternalApplicationError_when_called() {
        DefaultResponseHandler handler = new DefaultResponseHandler();
        TransferResponse response = new TransferResponse();

        InquiryServiceResultDTO resultDTO = new InquiryServiceResultDTO();
        handler.handleResponse(response, resultDTO);

        assertEquals("504", resultDTO.getReasonCode());
        assertEquals("Internal Application Error", resultDTO.getReasonDesc());
    }
}