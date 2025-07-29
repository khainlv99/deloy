package com.example.dncinema.service.show_room.impl;

import com.example.dncinema.dto.ShowRoomDTO;
import com.example.dncinema.model.ShowRoom;
import com.example.dncinema.repository.show_room.IShowRoomRepository;
import com.example.dncinema.service.show_room.IShowRoomService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowRoomService implements IShowRoomService {

    @Autowired
    private IShowRoomRepository iShowRoomRepository;


    @Override
    public Page<ShowRoomDTO> findShowRoomByName(Pageable pageable, String name) {
        Page<ShowRoom> showRoomPage = iShowRoomRepository.findShowRoomByName(pageable, name);
        List<ShowRoomDTO> showRoomDTOList = new ArrayList<>();
        ShowRoomDTO showRoomDTO;
        for (ShowRoom showRoom : showRoomPage) {
            showRoomDTO = new ShowRoomDTO();
            BeanUtils.copyProperties(showRoom, showRoomDTO);
            showRoomDTOList.add(showRoomDTO);
        }
        return new PageImpl<>(showRoomDTOList, pageable, showRoomPage.getTotalElements());
    }

    @Override
    public void addShowRoom(ShowRoomDTO showRoomDTO) {
         ShowRoom showRoom = new ShowRoom();
        BeanUtils.copyProperties(showRoomDTO, showRoom);
        iShowRoomRepository.addShowRoom(showRoom.getNameShowRoom(),showRoom.getQuantitySeat());
    }

    @Override
    public void deleteShowRoom(Integer id) {
        iShowRoomRepository.deleteShowRoom(id);
    }

    @Override
    public ShowRoomDTO findShowRoomById(Integer id) {
        ShowRoom showRoom = iShowRoomRepository.findShowRoomById(id);
        ShowRoomDTO showRoomDTO = new ShowRoomDTO();
        BeanUtils.copyProperties(showRoom, showRoomDTO);
        return showRoomDTO ;
    }

    @Override
    public void updateShowRoom(ShowRoomDTO showRoomDTO) {
        ShowRoom showRoom = new ShowRoom();
        BeanUtils.copyProperties(showRoomDTO, showRoom);
        iShowRoomRepository.updateShowRoom(showRoom.getNameShowRoom(),showRoom.getQuantitySeat(),showRoom.getIdShowRoom());
    }

    @Override
    public ShowRoom getShowRoomById(int id) {
        return iShowRoomRepository.findByIdShowRoom(id);
    }

    @Override
    public List<ShowRoom> getAllShowRoom() {
        return iShowRoomRepository.findAll();
    }

}
