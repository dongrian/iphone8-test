package com.gaeasoft.preorder.remote.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Header {
    private String responseMessage;
    private String responseCode;
    private String totalCount;
    private String totoalCount;
    
    public String getResponseMessage() {
        return responseMessage;
    }
    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
    public String getResponseCode() {
        return responseCode;
    }
    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }
    public String getTotalCount() {
        return totalCount;
    }
    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }
    @Override
    public String toString() {
        return "Header [responseMessage=" + responseMessage + ", responseCode=" + responseCode
                + ", totalCount=" + totalCount + "]";
    }
    public String getTotoalCount() {
        return totoalCount;
    }
    public void setTotoalCount(String totoalCount) {
        this.totoalCount = totoalCount;
    }
    
}
