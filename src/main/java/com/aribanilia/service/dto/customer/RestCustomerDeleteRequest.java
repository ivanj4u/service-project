package com.aribanilia.service.dto.customer;

import com.aribanilia.service.dto.RestInquiryRequest;

public class RestCustomerDeleteRequest extends RestInquiryRequest {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
