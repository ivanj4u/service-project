package com.aribanilia.service.dto.customer;

import com.aribanilia.service.dto.RestInquiryRequest;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

public class RestCustomerDeleteRequest extends RestInquiryRequest {

    @Valid
    @NotBlank
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
