package com.example.learningspring.controller;

import com.example.learningspring.data.entity.Room;
import com.example.learningspring.data.repository.RoomRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rooms")
public class RoomController {
    private final RoomRepository roomRepository;

    public RoomController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @GetMapping
    public Iterable<Room> getRooms() {
        return this.roomRepository.findAll();
    }
}
