package com.example.dncinema.service.showtime;

import com.example.dncinema.model.ShowTime;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface IShowTimeService {
    List<ShowTime> findAllDateByIdFilm(Integer id);
    List<ShowTime> findAllTimeByIdFilmAndShowDate(Integer id, String showDate);
    void save(ShowTime showTime);
    Optional<ShowTime> findById(Integer id);
    void deleteById(Integer id);
    List<ShowTime> listShowTime();
}
