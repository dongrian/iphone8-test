package com.gaeasoft.preorder.remote.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Orders {
	@JsonProperty("list")
    private List<Order> orderList;

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }
}
