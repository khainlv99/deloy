package com.example.dncinema.service.show_room.impl;

import com.example.dncinema.model.TypeSeat;
import com.example.dncinema.repository.show_room.ITypeSeatRepository;
import com.example.dncinema.service.show_room.ITypeSeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TypeSeatService implements ITypeSeatService {
    @Autowired
    private ITypeSeatRepository iTypeSeatRepository;
    @Override
    public List<TypeSeat> findAllTypeSeat() {
        return iTypeSeatRepository.findAllTypeSeat();
    }
}
