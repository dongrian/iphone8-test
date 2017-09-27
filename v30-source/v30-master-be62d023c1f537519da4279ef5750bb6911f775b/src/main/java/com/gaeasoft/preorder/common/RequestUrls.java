/*
 * Copyright (c) 2016 gaeasoft.co.kr to Present.
 * All rights reserved.
 */
package com.gaeasoft.preorder.common;

public class RequestUrls {
    
    // 키워드로 장소검색 API
    public static final String LOCAL_SEARCH_KEYWORD_URL = "https://apis.daum.net/local/v1/search/keyword.json";
    
    // 카테고리로 장소검색 API
    public static final String LOCAL_SEARCH_CATEGORY_URL = "https://apis.daum.net/local/v1/search/category.json";
    
    // 좌표계변환 API
    public static final String LOCAL_TRANSCOORD_URL = "https://apis.daum.net/local/geo/transcoord";
    
    // 좌표를 주소로 변환 API
    public static final String LOCAL_COORD_TO_ADDR_URL = "https://apis.daum.net/local/geo/coord2addr";

    // 장소정보 페이지 URL
    public static final String PLACE_MAP_URL= "http://place.map.daum.net/";
    
    // 장소검색 페이지 URL
    public static final String PLACE_SEARCHVIEW_URL = "https://m.map.daum.net/actions/searchView";
    
    // 정적 지도이미지 URL
    public static final String STATIC_MAP_IMAGE_URL = "http://map3.daum.net/staticmap/og";
    
}
