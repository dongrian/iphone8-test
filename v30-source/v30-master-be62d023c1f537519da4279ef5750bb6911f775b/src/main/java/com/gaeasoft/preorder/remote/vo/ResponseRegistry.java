package com.gaeasoft.preorder.remote.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseRegistry {
    
    @JsonProperty("BODY")
    private Registry body;
    
    @JsonProperty("HEADER")
    private Header header;
    
    public Registry getBody() {
        return body;
    }
    public void setBody(Registry body) {
        this.body = body;
    }
    public Header getHeader() {
        return header;
    }
    public void setHeader(Header header) {
        this.header = header;
    }
    
    public class Registry {
        private String reqSeqNo;

        public String getReqSeqNo() {
            return reqSeqNo;
        }

        public void setReqSeqNo(String reqSeqNo) {
            this.reqSeqNo = reqSeqNo;
        }
    }
    
}
