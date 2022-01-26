package com.tyco.room_scheduler;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface MeetingsRepo extends CrudRepository<Meeting, Integer> {
    public List<Meeting> findByIdIn(List<Integer> roomsId);

    public List<Meeting> findByIdInAndStartDateBetweenOrEndDateBetween(List<Integer> roomsId, Date startDate1,
            Date endDate1, Date startDate2, Date endDate2);

}
