package com.aribanilia.service.dto.customer;

import com.aribanilia.service.dto.RestInquiryRequest;

public class RestCustomerUpdateRequest extends RestInquiryRequest {

    private String id;
    private String name;
    private String birthDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
