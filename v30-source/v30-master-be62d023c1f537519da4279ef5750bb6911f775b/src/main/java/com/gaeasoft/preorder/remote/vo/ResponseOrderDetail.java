package com.gaeasoft.preorder.remote.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseOrderDetail {
    @JsonProperty("BODY")
    private String body;
    @JsonProperty("HEADER")
    private Header header;
    
    private OrderDetail orderDetail;
    
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
    public OrderDetail getOrderDetail() {
        return orderDetail;
    }
    public void setOrderDetail(OrderDetail orderDetail) {
        this.orderDetail = orderDetail;
    }

}
