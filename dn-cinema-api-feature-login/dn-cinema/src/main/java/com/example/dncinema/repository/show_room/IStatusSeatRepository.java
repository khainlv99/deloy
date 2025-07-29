package com.example.dncinema.repository.show_room;

import com.example.dncinema.model.StatusSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IStatusSeatRepository extends JpaRepository<StatusSeat,Integer> {
    @Query(value = "select * from status_seat", nativeQuery = true)
    List<StatusSeat> findAllStatusSeat();

}
