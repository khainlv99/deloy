package com.example.dncinema.service.show_room.impl;

import com.example.dncinema.model.StatusSeat;
import com.example.dncinema.repository.show_room.IStatusSeatRepository;
import com.example.dncinema.service.show_room.IStatusSeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StatusSeatService implements IStatusSeatService {

    @Autowired
    private IStatusSeatRepository iStatusSeatRepository;
    @Override
    public List<StatusSeat> findAllStatusSeat() {
        return iStatusSeatRepository.findAllStatusSeat();
    }
}
