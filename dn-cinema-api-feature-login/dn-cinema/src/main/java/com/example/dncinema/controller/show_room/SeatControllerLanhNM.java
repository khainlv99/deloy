package com.example.dncinema.controller.show_room;

import com.example.dncinema.model.Seat;
import com.example.dncinema.repository.show_room.ISeatRepositoryLanhNM;
import com.example.dncinema.service.show_room.ISeatServiceLanhNM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/seat")
@CrossOrigin("*")
public class SeatControllerLanhNM {
    @Autowired
    private ISeatServiceLanhNM iSeatServiceLanhNM;
    @Autowired
    private ISeatRepositoryLanhNM iSeatRepositoryLanhNM;


    @GetMapping("/list/{id}")
    public ResponseEntity<List<Seat>> getAllListSeatByIdShowRoom(@PathVariable Integer id){
        return new ResponseEntity<>(iSeatRepositoryLanhNM.findByShowRoom_IdShowRoom(id), HttpStatus.OK);
    }

    @PutMapping("/update_status")
    public ResponseEntity<?> updateStatusSeatByIdShowRoom(@RequestBody String[] listId) {
        for (String id : listId) {
            iSeatServiceLanhNM.updateStatusSeatByIdShowRoom(Integer.parseInt(id));
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/reset_status")
    public ResponseEntity<?> resetStatusSeatByIdShowRoom(@RequestBody String[] listId) {
        for (String id : listId) {
            iSeatServiceLanhNM.resetStatusSeatByIdShowRoom(Integer.parseInt(id));
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update_type_vip/{id}")
    public ResponseEntity<?> updateTypeSeatVipByIdShowRoom(@PathVariable Integer id) {

        iSeatServiceLanhNM.updateTypeSeatVipByIdShowRoom(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update_type_normal/{id}")
    public ResponseEntity<?> updateTypeSeatNormalByIdShowRoom(@PathVariable Integer id) {

        iSeatServiceLanhNM.updateTypeSeatNormalByIdShowRoom(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
