/*
 * Copyright (c) 2016 gaeasoft.co.kr to Present.
 * All rights reserved.
 */
package com.gaeasoft.preorder.remote.vo.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlaceInfo {
    private Integer count;
    private Integer page;
    private Integer totalCount;
    
    public PlaceInfo() {
    }
    
    public Integer getCount() {
        return count;
    }
    
    public void setCount(Integer count) {
        this.count = count;
    }
    
    public Integer getPage() {
        return page;
    }
    
    public void setPage(Integer page) {
        this.page = page;
    }
    
    public Integer getTotalCount() {
        return totalCount;
    }
    
    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }
    
    @Override
    public String toString() {
        return "Info [count=" + count + ", page=" + page + ", totalCount=" + totalCount + "]";
    }
    
}
