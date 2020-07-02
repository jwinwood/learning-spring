package com.example.learningspring.web;

import com.example.learningspring.business.domain.RoomReservation;
import com.example.learningspring.business.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/reservations")
public class RoomReservationWebController {

    private final ReservationService reservationService;

    @Autowired
    public RoomReservationWebController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public String getReservations(@RequestParam(value = "date", required = false) String dateString, Model model) {
        var date = DateUtils.createLocalDateFromDateString(dateString);
        List<RoomReservation> roomReservations = reservationService.getRoomReservationsForDate(date);

        model.addAttribute("roomReservations", roomReservations);

        return "reservations";
    }
}
