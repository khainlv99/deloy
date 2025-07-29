package com.example.dncinema.repository.showtime;

import com.example.dncinema.model.Film;
import com.example.dncinema.model.ShowTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IShowTimeRepository extends JpaRepository<ShowTime, Integer> {
    @Query(value = "select * from show_time where id_film = :id", nativeQuery = true)
    List<ShowTime> findAllDateByIdFilm(@Param("id") Integer id);
    @Query(value = "select * from show_time where id_film = :id and show_date = :showDate", nativeQuery = true)
    List<ShowTime> findAllDateByIdFilmAndShowDate(@Param("id") Integer id, @Param("showDate") String showDate);
}
