package com.example.dncinema.service.seat.impl;

import com.example.dncinema.model.Seat;
import com.example.dncinema.repository.seat.ISeatRepository;
import com.example.dncinema.service.seat.ISeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService implements ISeatService {
    @Autowired
    private ISeatRepository seatRepository;

    @Override
    public List<Seat> findAllListSeatByIdShowTime(Integer id) {
        return seatRepository.findAllListSeatByIdShowTime(id);
    }

    @Override
    public void updateStatusSeatByIdShowTime(Integer id) {
        seatRepository.updateStatusSeatByIdShowTime(id);
    }

    @Override
    public void resetStatusSeatByIdShowTime(Integer id) {
        seatRepository.resetStatusSeatByIdShowTime(id);
    }


}
