package com.tyco.room_scheduler;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoomSchedulerController {

    @Autowired
    SchedulingService schedulingService;

    @GetMapping("/find")
    public List<Room> retrieveAvailableRooms(Date startDate, long meetingTimeSpanInMinutes, int numberAttendees,
            int requiresMultimedia) {
        return schedulingService.findAvailableRooms(startDate, meetingTimeSpanInMinutes, numberAttendees,
                requiresMultimedia);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Meeting scheduleMeeting(int roomId, Date startDate, long meetingTimeSpanInMinutes) {
        System.out.println(startDate);
        return schedulingService.scheduleMeetingRoom(roomId, startDate, meetingTimeSpanInMinutes);
    }

}
