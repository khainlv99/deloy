package com.example.dncinema.service.seat;

import com.example.dncinema.model.Film;
import com.example.dncinema.model.Seat;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ISeatService {
    List<Seat> findAllListSeatByIdShowTime(Integer id);
    void updateStatusSeatByIdShowTime(Integer id);
    void resetStatusSeatByIdShowTime(Integer id);
}
