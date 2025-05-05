package com.ascendcorp.exam.handler;

import com.ascendcorp.exam.model.InquiryServiceResultDTO;
import com.ascendcorp.exam.model.TransferResponse;

public interface ResponseHandler {
    void handleResponse(TransferResponse response, InquiryServiceResultDTO respDTO);
}