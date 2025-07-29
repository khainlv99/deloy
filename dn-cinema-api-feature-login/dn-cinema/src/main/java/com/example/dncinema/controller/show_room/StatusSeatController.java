package com.example.dncinema.controller.show_room;

import com.example.dncinema.dto.ShowRoomDTO;
import com.example.dncinema.model.StatusSeat;
import com.example.dncinema.repository.show_room.IStatusSeatRepository;
import com.example.dncinema.service.show_room.IStatusSeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/status-seat")
@CrossOrigin("*")
public class StatusSeatController {
    @Autowired
    private IStatusSeatService iStatusSeatService;
    /**
     * @return ResponseEntity<>(list, HttpStatus.OK);
     * Phương thức sử dụng để tìm kiếm kết hợp danh sách trạng thái ghế
     * @author LanhNM
     */

    @GetMapping("/list")
    public List<StatusSeat> list() {
        List<StatusSeat> list = iStatusSeatService.findAllStatusSeat();
        if (list.isEmpty()) {
            return new ResponseEntity<>(list, HttpStatus.BAD_REQUEST).getBody();
        }
        return new ResponseEntity<>(list, HttpStatus.OK).getBody();
    }

}
