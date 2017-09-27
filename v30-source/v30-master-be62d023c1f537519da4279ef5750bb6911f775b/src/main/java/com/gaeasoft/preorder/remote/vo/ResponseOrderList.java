package com.gaeasoft.preorder.remote.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseOrderList {
    @JsonProperty("BODY")
    private String body;
    
    @JsonProperty("HEADER")
    private Header header;
    
    private Orders orders;
    
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

}
