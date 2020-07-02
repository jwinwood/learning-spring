package com.example.learningspring.business.service;

import com.example.learningspring.business.domain.RoomReservation;
import com.example.learningspring.data.entity.Guest;
import com.example.learningspring.data.entity.Room;
import com.example.learningspring.data.repository.GuestRepository;
import com.example.learningspring.data.repository.ReservationRepository;
import com.example.learningspring.data.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService {
    private final RoomRepository roomRepository;
    private final GuestRepository guestRepository;
    private final ReservationRepository reservationRepository;

    public ReservationService(RoomRepository roomRepository, GuestRepository guestRepository, ReservationRepository reservationRepository) {
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
        this.reservationRepository = reservationRepository;
    }

    public List<RoomReservation> getRoomReservationsForDate(LocalDate date) {
        List<RoomReservation> roomReservations = new ArrayList<>();

        roomRepository.findAll().forEach(room -> {
            RoomReservation roomReservation = new RoomReservation();
            roomReservation.setRoomId(room.getRoomId());
            roomReservation.setRoomName(room.getName());
            roomReservation.setRoomNumber(room.getRoomNumber());
            roomReservations.add(roomReservation);
        });

        reservationRepository.findReservationByReservationDate(Date.valueOf(date))
                .forEach(reservation -> {
                    Room room = roomRepository.findById(reservation.getRoomId()).orElseThrow();

                    RoomReservation roomReservation = new RoomReservation();
                    roomReservation.setRoomId(room.getRoomId());
                    roomReservation.setRoomName(room.getName());
                    roomReservation.setRoomNumber(room.getRoomNumber());

                    Guest guest = guestRepository.findById(reservation.getGuestId()).orElseThrow();

                    roomReservation.setFirstName(guest.getFirstName());
                    roomReservation.setLastName(guest.getLastName());
                    roomReservation.setGuestId(guest.getGuestId());

                    roomReservations.add(roomReservation);
                });
        return roomReservations;
    }
}
