package com.masteringapi.meetings.controller;

import com.masteringapi.meetings.model.Meeting;
import com.masteringapi.meetings.model.MeetingSummary;
import com.masteringapi.meetings.model.ValueWrapper;
import com.masteringapi.meetings.service.MeetingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MeetingsController {

    private MeetingService meetingService = new MeetingService();

    @GetMapping("/meetings")
    @ResponseBody
    public ValueWrapper<MeetingSummary> getMeetings() {
        return new ValueWrapper<MeetingSummary>(meetingService.getMeetingSummaries());
    }

    @GetMapping("/meetings/{id}")
    @ResponseBody
    public Meeting getMeeting(@PathVariable Integer id) {
        return meetingService.getMeetingDetail(id);
    }
}
