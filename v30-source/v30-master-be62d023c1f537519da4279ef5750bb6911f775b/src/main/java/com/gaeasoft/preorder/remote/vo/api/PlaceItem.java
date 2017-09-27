/*
 * Copyright (c) 2016 gaeasoft.co.kr to Present.
 * All rights reserved.
 */
package com.gaeasoft.preorder.remote.vo.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlaceItem {
    
    private String phone;
    private String newAddress;
    private String imageUrl;
    private String direction;
    private String zipcode;
    private String placeUrl;
    private Long id;
    private String title;
    private Long distance;
    private String category;
    private String address;
    private Double longitude;
    private Double latitude;
    private String addressBCode;
    
    public PlaceItem() {
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNewAddress() {
        return newAddress;
    }

    public void setNewAddress(String newAddress) {
        this.newAddress = newAddress;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getPlaceUrl() {
        return placeUrl;
    }

    public void setPlaceUrl(String placeUrl) {
        this.placeUrl = placeUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getDistance() {
        return distance;
    }

    public void setDistance(Long distance) {
        this.distance = distance;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getAddressBCode() {
        return addressBCode;
    }

    public void setAddressBCode(String addressBCode) {
        this.addressBCode = addressBCode;
    }

    @Override
    public String toString() {
        return "Item [phone=" + phone + ", newAddress=" + newAddress + ", imageUrl=" + imageUrl
                + ", direction=" + direction + ", zipcode=" + zipcode + ", placeUrl=" + placeUrl
                + ", id=" + id + ", title=" + title + ", distance=" + distance + ", category="
                + category + ", address=" + address + ", longitude=" + longitude + ", latitude="
                + latitude + ", addressBCode=" + addressBCode + "]";
    }
}
