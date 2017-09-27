/*
 * Copyright (c) 2016 gaeasoft.co.kr to Present.
 * All rights reserved.
 */
package com.gaeasoft.preorder.remote.vo.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlaceResponse {
    private PlaceChannel channel;

    public PlaceResponse() {
    }
    
    public PlaceChannel getChannel() {
        return channel;
    }

    public void setChannel(PlaceChannel channel) {
        this.channel = channel;
    }

    @Override
    public String toString() {
        return "Response [channel=" + channel + "]";
    }
    
    
}
