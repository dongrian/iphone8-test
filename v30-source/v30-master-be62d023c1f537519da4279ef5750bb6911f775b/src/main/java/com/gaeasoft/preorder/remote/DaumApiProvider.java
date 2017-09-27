package com.gaeasoft.preorder.remote;

import java.net.URI;
import java.net.URLEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import com.gaeasoft.preorder.common.RequestUrls;
import com.gaeasoft.preorder.remote.vo.api.Address;
import com.gaeasoft.preorder.remote.vo.api.PlaceChannel;
import com.gaeasoft.preorder.remote.vo.api.Coord;
import com.gaeasoft.preorder.remote.vo.api.CoordType;
import com.gaeasoft.preorder.remote.vo.api.PlaceResponse;

public class DaumApiProvider {
    private static final Logger log = LoggerFactory.getLogger(DaumApiProvider.class);
    
    public static PlaceChannel searchPlace(String apiKey, String query, Integer sort, Integer radius, Double latitude, Double longitude) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        
        StringBuffer url = new StringBuffer();
        url.append(RequestUrls.LOCAL_SEARCH_KEYWORD_URL)
        .append("?apiKey=" + apiKey)
        .append("&query=" + URLEncoder.encode(query, "UTF-8"))
        .append("&location=" + latitude + "," + longitude)
        .append("&sort=" + sort)
        .append("&radius=" + radius);
        
        URI uri = new URI(url.toString());
        
        log.info("### uri: {}", uri.toString());
        
        PlaceResponse reponse = restTemplate.getForObject(uri, PlaceResponse.class);
        
        return reponse.getChannel();
    }
    
    public static PlaceChannel searchPlaceByCategory(String apiKey, String categoryCode, Integer sort, Integer radius, Double latitude, Double longitude, Integer page) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        
        StringBuffer url = new StringBuffer();
        url.append(RequestUrls.LOCAL_SEARCH_CATEGORY_URL)
        .append("?apiKey=" + apiKey)
        .append("&code=" + categoryCode)
        .append("&location=" + latitude + "," + longitude)
        .append("&sort=" + sort)
        .append("&radius=" + radius)
        .append("&page=" + page);
        
        URI uri = new URI(url.toString());
        
        log.info("### uri: {}", uri.toString());
        
        PlaceResponse reponse = restTemplate.getForObject(uri, PlaceResponse.class);
        
        return reponse.getChannel();
    }
    
    public static Coord transcoordWgs84ToWcongnamul(String apiKey, Double longitude, Double latitude) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        
        StringBuffer url = new StringBuffer();
        url.append(RequestUrls.LOCAL_TRANSCOORD_URL)
        .append("?apiKey=" + apiKey)
        .append("&x=" + longitude)
        .append("&y=" + latitude)
        .append("&fromCoord=" + CoordType.WGS84)
        .append("&toCoord=" + CoordType.WCONGNAMUL)
        .append("&output=json");
        
        URI uri = new URI(url.toString());
        
        log.info("### uri: {}", uri.toString());
        
        Coord coord = restTemplate.getForObject(uri, Coord.class);
        
        return coord;
    }
    
    public static Coord transcoordWcongnamulToWgs84(String apiKey, Integer x, Integer y) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        
        StringBuffer url = new StringBuffer();
        url.append(RequestUrls.LOCAL_TRANSCOORD_URL)
        .append("?apiKey=" + apiKey)
        .append("&x=" + x)
        .append("&y=" + y)
        .append("&fromCoord=" + CoordType.WCONGNAMUL)
        .append("&toCoord=" + CoordType.WGS84)
        .append("&output=json");
        
        URI uri = new URI(url.toString());
        
        log.info("### uri: {}", uri.toString());
        
        Coord coord = restTemplate.getForObject(uri, Coord.class);
        
        return coord;
    }
    
    public static Address coordToAddr(String apiKey, Double longitude, Double latitude) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        StringBuffer url = new StringBuffer();
        url.append(RequestUrls.LOCAL_COORD_TO_ADDR_URL)
            .append("?apiKey=" + apiKey)
            .append("&longitude=" + longitude)
            .append("&latitude=" + latitude)
            .append("&output=json");
        
        URI uri = new URI(url.toString());
        
        log.info("### uri: {}", uri.toString());
        
        Address address = restTemplate.getForObject(uri, Address.class);
        
        return address;
    }
}
