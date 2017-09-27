package com.gaeasoft.preorder.remote.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseInventoryList {
    @JsonProperty("BODY")
    private Inventories body;
    
    @JsonProperty("HEADER")
    private Header header;
    
    
    public Inventories getBody() {
        return body;
    }

    public void setBody(Inventories body) {
        this.body = body;
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }


    public class Inventories {
        private List<Inventory> inventoryList;

        public List<Inventory> getInventoryList() {
            return inventoryList;
        }

        public void setInventoryList(List<Inventory> inventoryList) {
            this.inventoryList = inventoryList;
        }
    }
}
