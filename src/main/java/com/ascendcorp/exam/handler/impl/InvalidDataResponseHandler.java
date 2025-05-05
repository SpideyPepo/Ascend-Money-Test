package com.ascendcorp.exam.handler.impl;

import com.ascendcorp.exam.handler.ResponseHandler;
import com.ascendcorp.exam.model.InquiryServiceResultDTO;
import com.ascendcorp.exam.model.TransferResponse;

public class InvalidDataResponseHandler implements ResponseHandler {
    @Override
    public void handleResponse(TransferResponse response, InquiryServiceResultDTO respDTO) {
        String description = response.getDescription();
        if (description != null && description.contains(":")) {
            String[] parts = description.split(":");
            if (parts.length >= 3) {
                respDTO.setReasonCode(parts[1]);
                respDTO.setReasonDesc(parts[2]);
            } else {
                respDTO.setReasonCode("400");
                respDTO.setReasonDesc("General Invalid Data");
            }
        } else {
            respDTO.setReasonCode("400");
            respDTO.setReasonDesc("General Invalid Data");
        }
    }
}