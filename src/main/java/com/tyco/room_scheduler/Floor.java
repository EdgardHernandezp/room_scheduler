package com.tyco.room_scheduler;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Floor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    // private List<Room> rooms;
}
