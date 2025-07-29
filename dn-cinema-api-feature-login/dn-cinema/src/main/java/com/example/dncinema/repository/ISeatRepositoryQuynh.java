package com.example.dncinema.repository;

import com.example.dncinema.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ISeatRepositoryQuynh extends JpaRepository<Seat, Integer> {
    @Query(value = "select * from seat where id_show_room = :showRoomId and id_status_seat = :statusSeatId ", nativeQuery = true)
    Seat findSeatWithShowRomAndId(@Param("showRoomId") Integer showRoomId, @Param("statusSeatId") Integer statusSeatId);
}
