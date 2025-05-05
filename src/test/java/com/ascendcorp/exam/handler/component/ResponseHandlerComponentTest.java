package com.ascendcorp.exam.handler.component;

import com.ascendcorp.exam.handler.ResponseHandler;
import com.ascendcorp.exam.handler.impl.*;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ResponseHandlerComponentTest {

    @Test
    public void should_returnApprovedResponseHandler_when_responseCodeIsApproved() {
        ResponseHandlerComponent component = new ResponseHandlerComponent();
        ResponseHandler handler = component.getHandler("approved");
        assertTrue(handler instanceof ApprovedResponseHandler);
    }

    @Test
    public void should_returnInvalidDataResponseHandler_when_responseCodeIsInvalidData() {
        ResponseHandlerComponent component = new ResponseHandlerComponent();
        ResponseHandler handler = component.getHandler("invalid_data");
        assertTrue(handler instanceof InvalidDataResponseHandler);
    }

    @Test
    public void should_returnTransactionErrorResponseHandler_when_responseCodeIsTransactionError() {
        ResponseHandlerComponent component = new ResponseHandlerComponent();
        ResponseHandler handler = component.getHandler("transaction_error");
        assertTrue(handler instanceof TransactionErrorResponseHandler);
    }

    @Test
    public void should_returnUnknownResponseHandler_when_responseCodeIsUnknown() {
        ResponseHandlerComponent component = new ResponseHandlerComponent();
        ResponseHandler handler = component.getHandler("unknown");
        assertTrue(handler instanceof UnknownResponseHandler);
    }

    @Test
    public void should_returnDefaultResponseHandler_when_responseCodeIsNotMapped() {
        ResponseHandlerComponent component = new ResponseHandlerComponent();
        ResponseHandler handler = component.getHandler("unmapped_code");
        assertTrue(handler instanceof DefaultResponseHandler);
    }
}