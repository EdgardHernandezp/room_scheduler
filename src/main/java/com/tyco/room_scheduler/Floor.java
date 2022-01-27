package com.tyco.room_scheduler;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Entity(name = "floors")
@Data
public class Floor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToMany(mappedBy = "floor")
    @JsonManagedReference
    private List<Room> rooms;
    private int level;
}
