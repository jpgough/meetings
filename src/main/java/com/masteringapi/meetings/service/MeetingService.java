package com.masteringapi.meetings.service;

import com.masteringapi.meetings.model.Attendee;
import com.masteringapi.meetings.model.Meeting;
import com.masteringapi.meetings.model.MeetingSummary;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class MeetingService {

    private RestTemplate restTemplate = new RestTemplate();

    private final static String SERVICE_DEMO = "Service Demo";
    private final static String LUNCH = "Lunch";
    private final static String K8S = "Kubernetes Platform Demo";

    public List<MeetingSummary> getMeetingSummaries() {
        List<MeetingSummary> meetings = new ArrayList<>();
        meetings.add(new MeetingSummary(0, SERVICE_DEMO));
        meetings.add(new MeetingSummary(1, LUNCH));
        meetings.add(new MeetingSummary(2, K8S));
        return meetings;
    }

    public Meeting getMeetingDetail(Integer id) {
        Meeting meeting = new Meeting();
        if(id == 0) {
            meeting.setTitle(SERVICE_DEMO);
            meeting.setStartTime(LocalTime.of(10,0));
            meeting.setEndTime(LocalTime.of(11,0));
            meeting.setLocation("Zoom");
            List<Attendee> attendees = new ArrayList<>();
            ResponseEntity<Attendee> response = restTemplate.getForEntity("http://attendees-http/attendees/0", Attendee.class);
            attendees.add(response.getBody());
            meeting.setAttendees(attendees);
        } else if (id == 1) {
            meeting.setTitle(LUNCH);
            meeting.setStartTime(LocalTime.of(12,0));
            meeting.setEndTime(LocalTime.of(13,0));
            meeting.setLocation("Canteen");
            List<Attendee> attendees = new ArrayList<>();
            ResponseEntity<Attendee> response = restTemplate.getForEntity("http://attendees-http/attendees/0", Attendee.class);
            attendees.add(response.getBody());
            response = restTemplate.getForEntity("http://attendees-http/attendees/1", Attendee.class);
            attendees.add(response.getBody());
            meeting.setAttendees(attendees);
        } else if (id == 2) {
            meeting.setTitle(K8S);
            meeting.setStartTime(LocalTime.of(14,0));
            meeting.setEndTime(LocalTime.of(15,0));
            meeting.setLocation("Zoom");
            List<Attendee> attendees = new ArrayList<>();
            ResponseEntity<Attendee> response = restTemplate.getForEntity("http://attendees-http/attendees/0", Attendee.class);
            attendees.add(response.getBody());
            response = restTemplate.getForEntity("http://attendees-http/attendees/1", Attendee.class);
            attendees.add(response.getBody());
            response = restTemplate.getForEntity("http://attendees-http/attendees/2", Attendee.class);
            attendees.add(response.getBody());
            meeting.setAttendees(attendees);
        }
        return meeting;
    }
}
