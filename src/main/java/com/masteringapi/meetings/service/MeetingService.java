package com.masteringapi.meetings.service;

import com.masteringapi.meetings.model.Attendee;
import com.masteringapi.meetings.model.Meeting;
import com.masteringapi.meetings.model.MeetingSummary;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MeetingService {

    private RestTemplate restTemplate = new RestTemplate();
    private HeaderPropagation headerPropagation = new HeaderPropagation();

    private final static String SERVICE_DEMO = "Service Demo";
    private final static String LUNCH = "Lunch";
    private final static String K8S = "Kubernetes Platform Demo";
    private final static String ATTENDEES_BASE_URL = "http://app.attendees";

    public List<MeetingSummary> getMeetingSummaries() {
        List<MeetingSummary> meetings = new ArrayList<>();
        meetings.add(new MeetingSummary(0, SERVICE_DEMO));
        meetings.add(new MeetingSummary(1, LUNCH));
        meetings.add(new MeetingSummary(2, K8S));
        return meetings;
    }

    public Meeting getMeetingDetail(Integer id, Map<String, String> headers) {
        Meeting meeting = new Meeting();
        HttpHeaders requestHeaders = this.headerPropagation.captureTracingHeaders(headers);
        requestHeaders.add("Cache-Control", this.headerPropagation.bustAllCaching().getHeaderValue());
        HttpEntity<Void> requestEntity = new HttpEntity<>(requestHeaders);

        if(id == 0) {
            meeting.setTitle(SERVICE_DEMO);
            meeting.setStartTime(LocalTime.of(10,0));
            meeting.setEndTime(LocalTime.of(11,0));
            meeting.setLocation("Zoom");
            List<Attendee> attendees = new ArrayList<>();
            ResponseEntity<Attendee> response = restTemplate.exchange(ATTENDEES_BASE_URL + "/attendees/0", HttpMethod.GET,
                    requestEntity, Attendee.class);
            attendees.add(response.getBody());
            meeting.setAttendees(attendees);
        } else if (id == 1) {
            meeting.setTitle(LUNCH);
            meeting.setStartTime(LocalTime.of(12,0));
            meeting.setEndTime(LocalTime.of(13,0));
            meeting.setLocation("Canteen");
            List<Attendee> attendees = new ArrayList<>();
            ResponseEntity<Attendee> response = restTemplate.exchange(ATTENDEES_BASE_URL + "/attendees/0", HttpMethod.GET,
                    requestEntity, Attendee.class);
            attendees.add(response.getBody());
            response = restTemplate.exchange(ATTENDEES_BASE_URL + "/attendees/1", HttpMethod.GET,
                    requestEntity, Attendee.class);
            attendees.add(response.getBody());
            meeting.setAttendees(attendees);
        } else if (id == 2) {
            meeting.setTitle(K8S);
            meeting.setStartTime(LocalTime.of(14,0));
            meeting.setEndTime(LocalTime.of(15,0));
            meeting.setLocation("Zoom");
            List<Attendee> attendees = new ArrayList<>();
            ResponseEntity<Attendee> response = restTemplate.exchange(ATTENDEES_BASE_URL + "/attendees/0", HttpMethod.GET,
                    requestEntity, Attendee.class);
            attendees.add(response.getBody());
            response = restTemplate.exchange(ATTENDEES_BASE_URL + "/attendees/1", HttpMethod.GET,
                    requestEntity, Attendee.class);
            attendees.add(response.getBody());
            response = restTemplate.exchange(ATTENDEES_BASE_URL + "/attendees/2", HttpMethod.GET,
                    requestEntity, Attendee.class);
            attendees.add(response.getBody());
            meeting.setAttendees(attendees);
        }
        return meeting;
    }
}
