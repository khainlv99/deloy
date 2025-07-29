package com.example.dncinema.controller.show_room;

import com.example.dncinema.model.StatusSeat;
import com.example.dncinema.model.TypeSeat;
import com.example.dncinema.service.show_room.IStatusSeatService;
import com.example.dncinema.service.show_room.ITypeSeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin/type-seat")
@CrossOrigin("*")
public class TypeSeatController {
    @Autowired
    private ITypeSeatService iTypeSeatService;
    /**
     * @return ResponseEntity<>(list, HttpStatus.OK);
     * Phương thức sử dụng để tìm kiếm kết hợp danh sách loại ghế
     * @author LanhNM
     */

    @GetMapping("/list")
    public List<TypeSeat> list() {
        List<TypeSeat> list = iTypeSeatService.findAllTypeSeat();
        if (list.isEmpty()) {
            return new ResponseEntity<>(list, HttpStatus.BAD_REQUEST).getBody();
        }
        return new ResponseEntity<>(list, HttpStatus.OK).getBody();
    }
}
