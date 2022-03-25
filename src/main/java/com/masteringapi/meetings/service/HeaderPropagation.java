package com.masteringapi.meetings.service;

import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class HeaderPropagation {

    private final Set<String> acceptableHeaders;

    public HeaderPropagation() {
        this.acceptableHeaders = new HashSet<>();
        this.acceptableHeaders.add("x-request-id");
        this.acceptableHeaders.add("x-b3-traceid");
        this.acceptableHeaders.add("x-b3-spanid");
        this.acceptableHeaders.add("x-b3-parentspanid");
        this.acceptableHeaders.add("x-b3-sampled");
        this.acceptableHeaders.add("x-b3-flags");
        this.acceptableHeaders.add("x-ot-span-context");
    }

    public HttpHeaders captureTracingHeaders(Map<String, String> inboundHeaders) {
        HttpHeaders requestHeaders = new HttpHeaders();
        for(Map.Entry<String, String> item : inboundHeaders.entrySet()) {
            if(this.acceptableHeaders.contains(item.getKey())) {
                requestHeaders.add(item.getKey(), item.getValue());
            }
        }

        return requestHeaders;
    }

    public CacheControl bustAllCaching() {
        CacheControl cacheControl = CacheControl.noCache();
        cacheControl.noStore();
        cacheControl.mustRevalidate();
        cacheControl.proxyRevalidate();
        return cacheControl;
    }
}
