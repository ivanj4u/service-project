package com.aribanilia.service.dto.customer;

import com.aribanilia.service.dto.RestInquiryRequest;

public class RestCustomerAddRequest extends RestInquiryRequest {

    private String name;
    private String birthDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
}
