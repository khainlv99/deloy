package com.example.dncinema.repository;

import com.example.dncinema.dto.StatisticsDTO;
import com.example.dncinema.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IStatisticsRepository extends JpaRepository<Ticket, Integer> {
    @Query(value = "SELECT film.name_film as namefilm, COUNT(ticket.id_ticket) AS totalticketssold,\n" +
            "SUM(ticket.price_after_discount) AS totalrevenue\n" +
            "FROM film\n" +
            "LEFT JOIN show_time ON film.id_film = show_time.id_film\n" +
            "LEFT JOIN seat ON show_time.id_show_time = seat.id_show_time\n" +
            "LEFT JOIN ticket ON seat.id_seat = ticket.id_seat\n" +
            "GROUP BY film.name_film limit 4", nativeQuery = true)
    List<StatisticsDTO> findCommentSummaryByTitle();
    @Query(value = "select * from statis where namefilm like CONCAT('%', :namefilm, '%')", nativeQuery = true)
    List<StatisticsDTO> findStatisticsDTOByNameFilm(@Param("namefilm") String namefilm);
}
