package com.ascendcorp.exam.handler.impl;

import com.ascendcorp.exam.handler.ResponseHandler;
import com.ascendcorp.exam.model.InquiryServiceResultDTO;
import com.ascendcorp.exam.model.TransferResponse;

public class UnknownResponseHandler implements ResponseHandler {
    @Override
    public void handleResponse(TransferResponse response, InquiryServiceResultDTO respDTO) {
        String description = response.getDescription();
        if (description != null && description.contains(":")) {
            String[] parts = description.split(":");
            respDTO.setReasonCode(parts[0]);
            respDTO.setReasonDesc(parts.length > 1 ? parts[1] : "General Invalid Data");
        } else {
            respDTO.setReasonCode("501");
            respDTO.setReasonDesc("General Invalid Data");
        }
    }
}