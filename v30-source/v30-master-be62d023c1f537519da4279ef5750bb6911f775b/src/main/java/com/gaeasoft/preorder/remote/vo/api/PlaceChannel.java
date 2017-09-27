/*
 * Copyright (c) 2016 gaeasoft.co.kr to Present.
 * All rights reserved.
 */
package com.gaeasoft.preorder.remote.vo.api;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlaceChannel {
    private List<PlaceItem> item;
    private PlaceInfo info;
    
    public PlaceChannel() {
    }

    public List<PlaceItem> getItem() {
        return item;
    }

    public void setItem(List<PlaceItem> item) {
        this.item = item;
    }

    public PlaceInfo getInfo() {
        return info;
    }

    public void setInfo(PlaceInfo info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Channel [item=" + item + ", info=" + info + "]";
    }
    
}
