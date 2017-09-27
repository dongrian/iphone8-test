package com.gaeasoft.preorder.remote.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseReqInfo {
    
    @JsonProperty("BODY")
    private ReqInfo body;
    
    @JsonProperty("HEADER")
    private Header header;
    
    public ReqInfo getBody() {
        return body;
    }
    public void setBody(ReqInfo body) {
        this.body = body;
    }
    public Header getHeader() {
        return header;
    }
    public void setHeader(Header header) {
        this.header = header;
    }
    
}
