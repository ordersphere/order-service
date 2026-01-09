package com.ordersphere.domain.error;

import com.ordersphere.core.api.ErrorCode;

public enum OrderErrorCode {

    ORDER_NOT_FOUND(new ErrorCode(404,1001,0001));

    private final ErrorCode code;

    OrderErrorCode(ErrorCode code) {
        this.code = code;
    }

    public ErrorCode getCode() {
        return code;
    }


}
