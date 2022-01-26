package com.tyco.room_scheduler;

import java.util.Date;
import java.util.List;

public interface SchedulingService {
    public List<Room> findAvailableRooms(Date startDate, long meetingTimeSpanInMinutes, int numberAttendees,
            int requiresMultimedia);

    public Meeting scheduleMeetingRoom(int roomId, Date startDate, long meetingTimeSpanInMinutes);

}
