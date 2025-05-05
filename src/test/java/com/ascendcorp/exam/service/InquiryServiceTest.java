package com.ascendcorp.exam.service;

import com.ascendcorp.exam.handler.component.ResponseHandlerComponent;
import com.ascendcorp.exam.model.InquiryServiceResultDTO;
import com.ascendcorp.exam.model.TransferResponse;
import com.ascendcorp.exam.proxy.BankProxyGateway;
import com.ascendcorp.exam.utilities.ValidateRequestParameters;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.web.server.WebServerException;

import java.util.Date;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class InquiryServiceTest {

    @InjectMocks
    InquiryService inquiryService;

    @Mock
    BankProxyGateway bankProxyGateway;

    @Mock
    ValidateRequestParameters validateRequestParameters;

    @Mock
    ResponseHandlerComponent responseHandlerComponent;

    @Test
    public void should_return500_when_noRequireValue() {
        doThrow(new NullPointerException("Transaction id is required!"))
                .when(validateRequestParameters)
                .validateRequest(any(), any(), any(), any(), any(), anyDouble());

        InquiryServiceResultDTO inquiry = inquiryService.inquiry(null, new Date(),
                "Mobile", null,
                "BANK1", "4321000", 100d, "rrivsffv234c",
                "11223xfgt", null, null);

        assertNotNull(inquiry);
        assertEquals("500", inquiry.getReasonCode());
        assertEquals("General Invalid Data", inquiry.getReasonDesc());
    }


    @Test
    public void should_return200_when_bankApproved() {
        TransferResponse transferResponse = new TransferResponse();
        transferResponse.setResponseCode("approved");
        transferResponse.setDescription("approved");

        when(bankProxyGateway.requestTransfer(anyString(),any(),anyString(),anyString(),anyString(),
                anyDouble(),anyString(),anyString())).thenReturn(transferResponse);

        doNothing().when(validateRequestParameters).validateRequest(anyString(), any(), anyString(), anyString(), anyString(), anyDouble());
        when(responseHandlerComponent.getHandler(anyString())).thenReturn((response, respDTO) -> {
            respDTO.setReasonCode("200");
            respDTO.setReasonDesc(response.getDescription());
        });

        InquiryServiceResultDTO inquiry = inquiryService.inquiry("123456", new Date(),
                "Mobile", null,
                "BANK1", "4321000", 100d, "rrivsffv234c",
                "11223xfgt", null, null);

        assertNotNull(inquiry);
        assertEquals("200", inquiry.getReasonCode());
        assertEquals("approved", inquiry.getReasonDesc());
    }

    @Test
    public void should_return400_when_invalidDataWithoutDesc() {
        TransferResponse transferResponse = new TransferResponse();
        transferResponse.setResponseCode("invalid_data");

        when(bankProxyGateway.requestTransfer(anyString(),any(),anyString(),anyString(),anyString(),
                anyDouble(),anyString(),anyString())).thenReturn(transferResponse);

        when(responseHandlerComponent.getHandler(anyString())).thenReturn((response, respDTO) -> {
            respDTO.setReasonCode("400");
            respDTO.setReasonDesc("General Invalid Data");
        });

        InquiryServiceResultDTO inquiry = inquiryService.inquiry("123456", new Date(),
                "Mobile", null,
                "BANK1", "4321000", 100d, "rrivsffv234c",
                "11223xfgt", null, null);

        assertNotNull(inquiry);
        assertEquals("400", inquiry.getReasonCode());
        assertEquals("General Invalid Data", inquiry.getReasonDesc());
    }


    @Test
    public void should_return1091WithReasonDesc_when_invalidDataWithDescAndCode() {
        TransferResponse transferResponse = new TransferResponse();
        transferResponse.setResponseCode("invalid_data");
        transferResponse.setDescription("100:1091:Data type is invalid.");

        when(bankProxyGateway.requestTransfer(anyString(),any(),anyString(),anyString(),anyString(),
                anyDouble(),anyString(),anyString())).thenReturn(transferResponse);

        when(responseHandlerComponent.getHandler(anyString())).thenReturn((response, respDTO) -> {
            respDTO.setReasonCode("1091");
            respDTO.setReasonDesc("Data type is invalid.");
        });


        InquiryServiceResultDTO inquiry = inquiryService.inquiry("123456", new Date(),
                "Mobile", null,
                "BANK1", "4321000", 100d, "rrivsffv234c",
                "11223xfgt", null, null);

        assertNotNull(inquiry);
        assertEquals("1091", inquiry.getReasonCode());
        assertEquals("Data type is invalid.", inquiry.getReasonDesc());
    }

    @Test
    public void should_return501_when_unknownAndTextDesc() {
        TransferResponse transferResponse = new TransferResponse();
        transferResponse.setResponseCode("unknown");
        transferResponse.setDescription("General Invalid Data code 501");

        when(bankProxyGateway.requestTransfer(anyString(),any(),anyString(),anyString(),anyString(),
                anyDouble(),anyString(),anyString())).thenReturn(transferResponse);

        when(responseHandlerComponent.getHandler(anyString())).thenReturn((response, respDTO) -> {
            respDTO.setReasonCode("501");
            respDTO.setReasonDesc("General Invalid Data");
        });

        InquiryServiceResultDTO inquiry = inquiryService.inquiry("123456", new Date(),
                "Mobile", null,
                "BANK1", "4321000", 100d, "rrivsffv234c",
                "11223xfgt", null, null);

        assertNotNull(inquiry);
        assertEquals("501", inquiry.getReasonCode());
        assertEquals("General Invalid Data", inquiry.getReasonDesc());
    }

    @Test
    public void should_return504_when_errorDescNotSupport() {
        TransferResponse transferResponse = new TransferResponse();
        transferResponse.setResponseCode("not_support");
        transferResponse.setDescription("Not support");

        when(bankProxyGateway.requestTransfer(anyString(),any(),anyString(),anyString(),anyString(),
                anyDouble(),anyString(),anyString())).thenReturn(transferResponse);

        when(responseHandlerComponent.getHandler(anyString())).thenReturn((response, respDTO) -> {
            respDTO.setReasonCode("504");
            respDTO.setReasonDesc("Internal Application Error");
        });

        InquiryServiceResultDTO inquiry = inquiryService.inquiry("123456", new Date(),
                "Mobile", null,
                "BANK1", "4321000", 100d, "rrivsffv234c",
                "11223xfgt", null, null);

        assertNotNull(inquiry);
        assertEquals("504", inquiry.getReasonCode());
        assertEquals("Internal Application Error", inquiry.getReasonDesc());
    }

    @Test
    public void should_return504_when_responseNull() {

        when(bankProxyGateway.requestTransfer(anyString(),any(),anyString(),anyString(),anyString(),
                anyDouble(),anyString(),anyString())).thenReturn(null);

        InquiryServiceResultDTO inquiry = inquiryService.inquiry("123456", new Date(),
                "Mobile", null,
                "BANK1", "4321000", 100d, "rrivsffv234c",
                "11223xfgt", null, null);

        assertNotNull(inquiry);
        assertEquals("504", inquiry.getReasonCode());
        assertEquals("Internal Application Error", inquiry.getReasonDesc());
    }

    @Test
    public void should_return503_when_throwWebServiceException() {

        when(bankProxyGateway.requestTransfer(anyString(),any(),anyString(),anyString(),anyString(),
                anyDouble(),anyString(),anyString())).thenThrow(WebServerException.class);

        InquiryServiceResultDTO inquiry = inquiryService.inquiry("123456", new Date(),
                "Mobile", null,
                "BANK1", "4321000", 100d, "rrivsffv234c",
                "11223xfgt", null, null);

        assertNotNull(inquiry);
        assertEquals("504", inquiry.getReasonCode());
        assertEquals("Internal Application Error", inquiry.getReasonDesc());
    }

    @Test
    public void should_return503_when_socketTimeout() {

        WebServerException ex = new WebServerException("java.net.SocketTimeoutException error", null);

        when(bankProxyGateway.requestTransfer(anyString(),any(),anyString(),anyString(),anyString(),
                anyDouble(),anyString(),anyString())).thenThrow(ex);

        InquiryServiceResultDTO inquiry = inquiryService.inquiry("123456", new Date(),
                "Mobile", null,
                "BANK1", "4321000", 100d, "rrivsffv234c",
                "11223xfgt", null, null);

        assertNotNull(inquiry);
        assertEquals("503", inquiry.getReasonCode());
        assertEquals("Error timeout", inquiry.getReasonDesc());
    }

    @Test
    public void should_return503_when_connectionTimeout() {

        WebServerException ex = new WebServerException("Server Connection timed out", null);

        when(bankProxyGateway.requestTransfer(anyString(),any(),anyString(),anyString(),anyString(),
                anyDouble(),anyString(),anyString())).thenThrow(ex);

        InquiryServiceResultDTO inquiry = inquiryService.inquiry("123456", new Date(),
                "Mobile", null,
                "BANK1", "4321000", 100d, "rrivsffv234c",
                "11223xfgt", null, null);

        assertNotNull(inquiry);
        assertEquals("503", inquiry.getReasonCode());
        assertEquals("Error timeout", inquiry.getReasonDesc());
    }
}
