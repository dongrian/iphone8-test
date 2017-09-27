package com.gaeasoft.preorder.remote.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseToken {
    
    @JsonProperty("BODY")
    private Token body;
    
    @JsonProperty("HEADER")
    private Header header;
    
    public Token getBody() {
        return body;
    }
    public void setBody(Token body) {
        this.body = body;
    }
    public Header getHeader() {
        return header;
    }
    public void setHeader(Header header) {
        this.header = header;
    }
    
    public class Token {
        private String token;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
    
}
