package com.example.dncinema.repository.show_room;

import com.example.dncinema.model.ShowRoom;
import com.example.dncinema.model.TypeSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ITypeSeatRepository extends JpaRepository<TypeSeat,Integer> {

    @Query(value = "select * from type_seat", nativeQuery = true)
    List<TypeSeat> findAllTypeSeat();

}
