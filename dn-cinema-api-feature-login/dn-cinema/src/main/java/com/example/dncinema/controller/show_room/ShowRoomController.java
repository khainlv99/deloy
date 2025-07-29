package com.example.dncinema.controller.show_room;

import com.example.dncinema.dto.ShowRoomDTO;
import com.example.dncinema.model.ShowRoom;
import com.example.dncinema.service.show_room.ISeatServiceLanhNM;
import com.example.dncinema.service.show_room.IShowRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/showroom")
@CrossOrigin("*")
public class ShowRoomController {

    @Autowired
    private IShowRoomService iShowRoomService;

    @Autowired
    private ISeatServiceLanhNM iSeatServiceLanhNM;


    /**
     * @param pageable
     * @param search
     * @return ResponseEntity<>(listShowRoom, HttpStatus.OK;
     * Phương thức sử dụng để tìm kiếm kết hợp danh sách phòng chiếu
     * @author LanhNM
     */
    @GetMapping("/list")
    public ResponseEntity<Page<ShowRoomDTO
            >> list(@PageableDefault (value = 2) Pageable pageable,@RequestParam(value = "page", defaultValue = "0") int page,
                                  @RequestParam(required = false, defaultValue = "") String search) {
        pageable = PageRequest.of(page, 2);
        Page<ShowRoomDTO> listShowRoom = iShowRoomService.findShowRoomByName(pageable, search);
        if (listShowRoom.isEmpty()) {
            return new ResponseEntity<>(listShowRoom, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(listShowRoom, HttpStatus.OK);
    }

    /**
     * @param showRoomDTO
     * @param bindingResult
     * @return ResponseEntity<>(showroom, HttpStatus.CREATED;
     * Phương thức sử dụng để tạo mới phòng chiếu
     * @author LanhNM
     */
    @PostMapping("/create")
    public ResponseEntity<?> saveShowRoom(@Valid @RequestBody ShowRoomDTO showRoomDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            iShowRoomService.addShowRoom(showRoomDTO);
        } else {
            Map<String, String> map = new LinkedHashMap<>();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                if (!map.containsKey(error.getField())) {
                    map.put(error.getField(), error.getDefaultMessage());
                }
            }
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * @param id
     * @return
     * Phương thức sử dụng để xóa phòng chiếu theo id
     * @author LanhNM
     */

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteShowRoom(@PathVariable Integer id) {
        iShowRoomService.deleteShowRoom(id);
    }

    /**
     * @param id
     * @return ResponseEntity<>(showroom, HttpStatus.BAD_REQUEST or HttpStatus.OK);
     * Phương thức sử dụng để tìm kiếm phòng chiếu theo id
     * @author LanhNM
     */

    @GetMapping("/detail")
    @ResponseStatus(HttpStatus.OK)
    public ShowRoomDTO findByIdShowRoom(@RequestParam(value = "id") Integer id) {
        ShowRoomDTO showRoomDTO = iShowRoomService.findShowRoomById(id);
        if (showRoomDTO == null) {
            return (ShowRoomDTO) new ResponseEntity<>(HttpStatus.BAD_REQUEST).getBody();
        }
        return new ResponseEntity<>(showRoomDTO, HttpStatus.OK).getBody();
    }

    /**
     * @param showRoomDTO
     * @param bindingResult
     * @return ResponseEntity<>(showroom, HttpStatus.BAD_REQUEST or HttpStatus.OK);
     * Phương thức sử dụng để cập nhật phòng chiếu theo id
     * @author LanhNM
     */

    @PutMapping("")
    public ResponseEntity<?> updateShowRoom(@Validated @RequestBody ShowRoomDTO showRoomDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            iShowRoomService.updateShowRoom(showRoomDTO);
        } else {
            Map<String, String> map = new LinkedHashMap<>();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                if (!map.containsKey(error.getField())) {
                    map.put(error.getField(), error.getDefaultMessage());
                }
            }
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/list/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ShowRoom> getlByIdShowRoom(@PathVariable Integer id) {
        ShowRoom showRoom = iShowRoomService.getShowRoomById(id);
        if (showRoom == null) {
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(showRoom, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(){
        List<ShowRoom> showRoomList = iShowRoomService.getAllShowRoom();
        return new ResponseEntity<>(showRoomList, HttpStatus.OK);
    }
}

