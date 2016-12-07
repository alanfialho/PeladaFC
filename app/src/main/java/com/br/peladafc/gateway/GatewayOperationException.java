package com.br.peladafc.gateway;

/**
 * Created by alan on 05/11/2016.
 */

public class GatewayOperationException extends Exception {

    public GatewayOperationException(String message) {
        super(message);
    }

    public GatewayOperationException(Throwable cause) {
        super(cause);
    }

    public GatewayOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}
