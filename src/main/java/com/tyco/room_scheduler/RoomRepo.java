package com.tyco.room_scheduler;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface RoomRepo extends CrudRepository<Room, Integer> {
    public List<Room> findByMaxAllocationLessThanEqual(int numberAtendees);

    public List<Room> findByMaxAllocationGreaterThanEqualAndHasMultimediaCapabilities(int numberAtendees,
            int hasMultimediaCapabilities);
}
