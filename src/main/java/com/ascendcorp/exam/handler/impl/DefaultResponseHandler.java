package com.ascendcorp.exam.handler.impl;

import com.ascendcorp.exam.handler.ResponseHandler;
import com.ascendcorp.exam.model.InquiryServiceResultDTO;
import com.ascendcorp.exam.model.TransferResponse;

public class DefaultResponseHandler implements ResponseHandler {
    @Override
    public void handleResponse(TransferResponse response, InquiryServiceResultDTO respDTO) {
        respDTO.setReasonCode("504");
        respDTO.setReasonDesc("Internal Application Error");
    }
}