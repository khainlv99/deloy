package com.example.dncinema.service.show_room;

import com.example.dncinema.dto.ShowRoomDTO;
import com.example.dncinema.model.ShowRoom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IShowRoomService {

    Page<ShowRoomDTO> findShowRoomByName(Pageable pageable, String name);


    void addShowRoom(ShowRoomDTO showRoomDTO);

    void deleteShowRoom(Integer id);

    ShowRoomDTO findShowRoomById(Integer id);

    void updateShowRoom(ShowRoomDTO showRoomDTO);

    ShowRoom getShowRoomById(int id);
    List<ShowRoom> getAllShowRoom();
}
