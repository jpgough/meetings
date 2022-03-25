package com.masteringapi.meetings.controller;

import com.masteringapi.meetings.model.Meeting;
import com.masteringapi.meetings.model.MeetingSummary;
import com.masteringapi.meetings.model.ValueWrapper;
import com.masteringapi.meetings.service.HeaderPropagation;
import com.masteringapi.meetings.service.MeetingService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
public class MeetingsController {

    private MeetingService meetingService = new MeetingService();

    private RestTemplate restTemplate = new RestTemplate();

    private HeaderPropagation headerPropagation = new HeaderPropagation();

    @GetMapping("/meetings")
    @ResponseBody
    public ValueWrapper<MeetingSummary> getMeetings() {
        return new ValueWrapper<MeetingSummary>(meetingService.getMeetingSummaries());
    }

    @GetMapping("/meetings/{id}")
    @ResponseBody
    public Meeting getMeeting(@PathVariable Integer id, @RequestHeader Map<String, String> headers) {
        return meetingService.getMeetingDetail(id, headers);
    }

    @GetMapping("/external")
    @ResponseBody
    public String getExternalContent(@RequestHeader Map<String, String> headers) {
        HttpHeaders requestHeaders = this.headerPropagation.captureTracingHeaders(headers);
        requestHeaders.add("Host", "httpbin.org");
        requestHeaders.add("Cache-Control", this.headerPropagation.bustAllCaching().getHeaderValue());
        HttpEntity<Void> requestEntity = new HttpEntity<>(requestHeaders);

        ResponseEntity<String> response = restTemplate.exchange("http://52.55.211.119/get", HttpMethod.GET,
                requestEntity, String.class);
        return response.toString();
    }
}
