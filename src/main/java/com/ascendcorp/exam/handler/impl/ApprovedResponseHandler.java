package com.ascendcorp.exam.handler.impl;

import com.ascendcorp.exam.handler.ResponseHandler;
import com.ascendcorp.exam.model.InquiryServiceResultDTO;
import com.ascendcorp.exam.model.TransferResponse;

public class ApprovedResponseHandler implements ResponseHandler {
    @Override
    public void handleResponse(TransferResponse response, InquiryServiceResultDTO respDTO) {
        respDTO.setReasonCode("200");
        respDTO.setReasonDesc(response.getDescription());
        respDTO.setAccountName(response.getDescription());
    }
}