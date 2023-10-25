package com.shoshore.tinox.util;

/**
 * @author : tapiwanasheshoshore
 * @mailto : tapsshore@gmail.com
 * @created : 25/10/2023, Wednesday
 **/
public class RequestResponse {
    private RequestResponse() {
    }

    public static CustomerResponse getOKResponse() {
        CustomerResponse customerResponse = new CustomerResponse("OK", "");
        customerResponse.setSuccess(true);
        return customerResponse;
    }

    public static CustomerResponse getOKResponse(Object data) {
        CustomerResponse customerResponse = new CustomerResponse("OK", data);
        customerResponse.setSuccess(true);
        return customerResponse;
    }


    public static CustomerResponse getBADResponse() {

        return new CustomerResponse("BAD", "");
    }

    public static CustomerResponse getBADResponse(Object data) {

        return new CustomerResponse("BAD", data);
    }
}
