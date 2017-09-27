/*
 * Copyright (c) 2016 gaeasoft.co.kr to Present.
 * All rights reserved.
 */
package com.gaeasoft.preorder.remote.vo.api;

public class Address {

    private String type;
    private String code;
    private String name;
    private String fullName;
    private String regionId;
    private String name0;
    private String code1;
    private String name1;
    private String code2;
    private String name2;
    private String code3;
    private String name3;
    double x;
    double y;
    
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getName0() {
        return name0;
    }
    public void setName0(String name0) {
        this.name0 = name0;
    }
    public String getCode1() {
        return code1;
    }
    public void setCode1(String code1) {
        this.code1 = code1;
    }
    public String getName1() {
        return name1;
    }
    public void setName1(String name1) {
        this.name1 = name1;
    }
    public String getCode2() {
        return code2;
    }
    public void setCode2(String code2) {
        this.code2 = code2;
    }
    public String getName2() {
        return name2;
    }
    public void setName2(String name2) {
        this.name2 = name2;
    }
    public String getCode3() {
        return code3;
    }
    public void setCode3(String code3) {
        this.code3 = code3;
    }
    public String getName3() {
        return name3;
    }
    public void setName3(String name3) {
        this.name3 = name3;
    }
    public double getX() {
        return x;
    }
    public void setX(double x) {
        this.x = x;
    }
    public double getY() {
        return y;
    }
    public void setY(double y) {
        this.y = y;
    }
    public String getRegionId() {
        return regionId;
    }
    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }
    @Override
    public String toString() {
        return "Address [type=" + type + ", code=" + code + ", name=" + name + ", fullName="
                + fullName + ", regionId=" + regionId + ", name0=" + name0 + ", code1=" + code1
                + ", name1=" + name1 + ", code2=" + code2 + ", name2=" + name2 + ", code3=" + code3
                + ", name3=" + name3 + ", x=" + x + ", y=" + y + "]";
    }
}
