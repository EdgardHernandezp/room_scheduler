package com.tyco.room_scheduler;

import java.util.List;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Entity(name = "rooms")
@Data
public class Room implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int maxAllocation;
    private int hasMultimediaCapabilities; // TODO limit characters
    @OneToMany(mappedBy = "room")
    @JsonManagedReference
    private List<Meeting> meetings;
    @ManyToOne
    @JsonBackReference
    private Floor floor;
}
