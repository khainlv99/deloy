package com.example.dncinema.service.show_room.impl;

import com.example.dncinema.model.Seat;
import com.example.dncinema.repository.show_room.ISeatRepositoryLanhNM;
import com.example.dncinema.service.show_room.ISeatServiceLanhNM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SeatServiceLanhNM implements ISeatServiceLanhNM {
    @Autowired
    private ISeatRepositoryLanhNM iSeatRepositoryLanhNM;
    @Override
    public List<Seat> findAllListSeatByIdShowRoom(Integer id) {
        return iSeatRepositoryLanhNM.findAllListSeatByIdShowRoom(id);
    }

    @Override
    public void updateStatusSeatByIdShowRoom(Integer id) {
        iSeatRepositoryLanhNM.updateStatusSeatByIdShowRoom(id);
    }

    @Override
    public void resetStatusSeatByIdShowRoom(Integer id) {

    }


    @Override
    public void updateTypeSeatVipByIdShowRoom(Integer id) {
        iSeatRepositoryLanhNM.updateTypeSeatVipByIdShowRoom(id);
    }

    @Override
    public void updateTypeSeatNormalByIdShowRoom(Integer id) {
        iSeatRepositoryLanhNM.updateTypeSeatNormalByIdShowRoom(id);
    }


}
