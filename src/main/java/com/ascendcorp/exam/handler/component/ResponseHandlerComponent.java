package com.ascendcorp.exam.handler.component;

import com.ascendcorp.exam.handler.ResponseHandler;
import com.ascendcorp.exam.handler.impl.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ResponseHandlerComponent {

    private final Map<String, ResponseHandler> handlers = new HashMap<>();

    public ResponseHandlerComponent() {
        handlers.put("approved", new ApprovedResponseHandler());
        handlers.put("invalid_data", new InvalidDataResponseHandler());
        handlers.put("transaction_error", new TransactionErrorResponseHandler());
        handlers.put("unknown", new UnknownResponseHandler());
        handlers.put("default", new DefaultResponseHandler());
    }

    public ResponseHandler getHandler(String responseCode) {
        return handlers.getOrDefault(responseCode, handlers.get("default"));
    }
}