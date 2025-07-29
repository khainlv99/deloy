package com.example.dncinema.repository.show_room;

import com.example.dncinema.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ISeatRepositoryLanhNM extends JpaRepository<Seat,Integer> {
    @Query(value = "select * from seat where id_show_show = :id", nativeQuery = true)
    List<Seat> findAllListSeatByIdShowRoom(@Param("id") Integer id);
    @Modifying
    @Transactional
    @Query(value = "update seat set id_status_seat = 3 where id_seat = :id", nativeQuery = true)
    void updateStatusSeatByIdShowRoom(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query(value = "update seat set id_status_seat = 2 where id_seat = :id", nativeQuery = true)
    void resetStatusSeatByIdShowRoom(@Param("id") Integer id);
    List<Seat> findByShowRoom_IdShowRoom(Integer id);


    @Modifying
    @Transactional
    @Query(value = "update seat set id_type_seat = 1 where id_seat = :id", nativeQuery = true)
    void updateTypeSeatVipByIdShowRoom(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query(value = "update seat set id_type_seat = 2 where id_seat = :id", nativeQuery = true)
    void updateTypeSeatNormalByIdShowRoom(@Param("id") Integer id);
}
