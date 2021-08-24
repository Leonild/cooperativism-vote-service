package com.cooperativismvoteservice.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CPFValidator {

    String status;

    public CPFValidator(){}

    @JsonProperty
    public String getStatus() {
        return status;
    }

    @JsonProperty
    public void setStatus(String status) {
        this.status = status;
    }

}
