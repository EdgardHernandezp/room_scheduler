package com.tyco.room_scheduler;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MultimediaRoomsSchedulingService implements SchedulingService {

    @Autowired
    RoomRepo roomRepo;

    @Autowired
    MeetingsRepo meetingRepo;

    @Override
    public List<Room> findAvailableRooms(Date startDate, long meetingTimeSpanInMinutes, int numberAttendees,
            int requiresMultimedia) {
        System.out.println("searching available rooms with " + numberAttendees + " and " + requiresMultimedia);
        List<Room> potentialRooms = roomRepo.findByMaxAllocationGreaterThanEqualAndHasMultimediaCapabilities(
                numberAttendees, requiresMultimedia);
        System.out.println("size: " + potentialRooms.size());
        List<Integer> potentialRoomsId = potentialRooms.stream().map(room -> room.getId()).collect(Collectors.toList());
        System.out.println("got potential roomsId");
        Date endDate = new Date(startDate.getTime() + (meetingTimeSpanInMinutes * 60 * 1000));
        List<Meeting> collisionMeetings = meetingRepo.findByIdInAndStartDateBetweenOrEndDateBetween(potentialRoomsId,
                startDate, endDate, startDate, endDate);
        System.out.println("got collision meetings");
        List<Integer> collisionRoomsId = collisionMeetings.stream().map(meeting -> meeting.getRoom().getId())
                .collect(Collectors.toList());
        System.out.println("got collision rooms Id");
        List<Room> suitableRooms = potentialRooms.stream().filter((room) -> !collisionRoomsId.contains(room.getId()))
                .collect(
                        Collectors.toList());
        Comparator<Room> roomComparator = (room1,
                room2) -> room1.getMaxAllocation() - numberAttendees > room2.getMaxAllocation() - numberAttendees ? 1
                        : -1;
        Collections.sort(suitableRooms, roomComparator);
        return suitableRooms;
    }

    @Override
    public Meeting scheduleMeetingRoom(int roomId, Date startDate, long meetingTimeSpanInMinutes) {
        System.out.println("scheduling meeting");
        Meeting meeting = new Meeting();
        Room room = roomRepo.findById(roomId).orElseThrow();
        meeting.setRoom(room);
        meeting.setStartDate(startDate);
        long endDateInMili = startDate.getTime()
                + ((meetingTimeSpanInMinutes + 5 + room.getMaxAllocation()) * 60 * 1000);
        meeting.setEndDate(new Date(endDateInMili));
        return meetingRepo.save(meeting);
    }

}
