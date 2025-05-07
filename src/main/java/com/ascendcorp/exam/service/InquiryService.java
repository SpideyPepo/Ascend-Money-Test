package com.ascendcorp.exam.service;

import com.ascendcorp.exam.handler.component.ResponseHandlerComponent;
import com.ascendcorp.exam.model.InquiryServiceResultDTO;
import com.ascendcorp.exam.model.TransferResponse;
import com.ascendcorp.exam.proxy.BankProxyGateway;
import com.ascendcorp.exam.utilities.ValidateRequestParameters;
import org.apache.log4j.Logger;
import org.springframework.boot.web.server.WebServerException;

import java.util.Date;

public class InquiryService {

    BankProxyGateway bankProxyGateway;
    ValidateRequestParameters validateRequestParameters;
    ResponseHandlerComponent responseHandlerComponent;

    private static final Logger log = Logger.getLogger(InquiryService.class);

    public InquiryServiceResultDTO inquiry(String transactionId,
                                           Date tranDateTime,
                                           String channel,
                                           String bankCode,
                                           String bankNumber,
                                           double amount,
                                           String reference1,
                                           String reference2) {
        InquiryServiceResultDTO respDTO = new InquiryServiceResultDTO();
        try {
            log.info("Validating request parameters.");
            validateRequestParameters.validateRequest(transactionId, tranDateTime, channel, bankCode, bankNumber, amount);

            log.info("Calling bank web service.");
            TransferResponse response = bankProxyGateway.requestTransfer(transactionId, tranDateTime, channel,
                    bankCode, bankNumber, amount, reference1, reference2);

            log.info("check bank response code");
            if (response == null) {
                throw new Exception("Unable to inquiry from service.");
            }

            log.debug("found response code");
            log.info("Processing bank response.");
            responseHandlerComponent.getHandler(response.getResponseCode()).handleResponse(response, respDTO);

        } catch (NullPointerException e) {
            log.error("Validation error: " + e.getMessage());
            respDTO.setReasonCode("500");
            respDTO.setReasonDesc("General Invalid Data");
        } catch (WebServerException e) {
            handleWebServiceException(e, respDTO);
        } catch (Exception e) {
            log.error("Inquiry exception", e);
            respDTO.setReasonCode("504");
            respDTO.setReasonDesc("Internal Application Error");
        }
        return respDTO;
    }

    private void handleWebServiceException(WebServerException e, InquiryServiceResultDTO respDTO) {
        String faultString = e.getMessage();
        if (faultString != null && (faultString.contains("SocketTimeoutException") || faultString.contains("Connection timed out"))) {
            respDTO.setReasonCode("503");
            respDTO.setReasonDesc("Error timeout");
        } else {
            respDTO.setReasonCode("504");
            respDTO.setReasonDesc("Internal Application Error");
        }
    }
}