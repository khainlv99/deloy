package com.example.dncinema.controller;

import com.example.dncinema.dto.ShowTimeDTO;
import com.example.dncinema.model.Film;
import com.example.dncinema.model.ShowTime;
import com.example.dncinema.service.showtime.IShowTimeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/public/showtime")
@CrossOrigin("*")
public class ShowTimeController {
    @Autowired
    private IShowTimeService showTimeService;

    /**
     * @author HaiPH
     * @param id
     * @return ResponseEntity<>(ListShowTime, HttpStatus)
     * @Usage_method Returns all showTime by idFilm in the database
     */
    @GetMapping("/{id}")
    public ResponseEntity<List<ShowTime>> getAllDateByIdFilm(@PathVariable Integer id){
        List<ShowTime> showTimes = showTimeService.findAllDateByIdFilm(id);
        if(showTimes.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(showTimes, HttpStatus.OK);
    }

    /**
     * @author HaiPH
     * @param id
     * @param showDate
     * @return ResponseEntity<>(ListShowTime, HttpStatus)
     * @Usage_method Returns all showTime by idFilm and date in the database
     */
    @GetMapping("/{id}/{showDate}")
    public ResponseEntity<List<ShowTime>> getAllTimeByIdFilmAndShowDate(@PathVariable Integer id,@PathVariable String showDate){
        List<ShowTime> showTimes = showTimeService.findAllTimeByIdFilmAndShowDate(id,showDate);
        if(showTimes.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(showTimes, HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<?> createShowTime(@Valid @RequestBody ShowTimeDTO showTimeDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        ShowTime showTime = new ShowTime();
        BeanUtils.copyProperties(showTimeDTO,showTime);
//        if (showTimeDTO.getShowRoom().getIdShowRoom().equals(showTime.getShowRoom().getIdShowRoom())){
//            if (showTimeDTO.getShowDate().equals(showTime.getShowDate())){
//                if (showTimeDTO.getShowTime().equals(showTime.getShowTime())){
//                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//                }else {
//                    showTimeService.save(showTime);
//                }
//            }
//        }
        showTime.getShowRoom().setIdShowRoom(showTimeDTO.getShowRoom().getIdShowRoom());
        showTime.getFilm().setIdFilm(showTimeDTO.getFilm().getIdFilm());
        showTimeService.save(showTime);
        return new ResponseEntity<>(showTimeDTO,HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateShowTime(@Valid @RequestBody ShowTimeDTO showTimeDTO,@PathVariable Integer id, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        ShowTime showTime = new ShowTime();
        Optional<ShowTime> showTimeOptional = showTimeService.findById(id);
        BeanUtils.copyProperties(showTimeOptional,showTimeDTO);
        showTimeDTO.setIdShowTime(id);
        BeanUtils.copyProperties(showTimeDTO,showTime);
        showTime.setIdShowTime(id);
        showTime.getShowRoom().setIdShowRoom(showTimeDTO.getShowRoom().getIdShowRoom());
        showTime.getFilm().setIdFilm(showTimeDTO.getFilm().getIdFilm());
        showTimeService.save(showTime);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteShowTime(@PathVariable Integer id){
        showTimeService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("")
    public ResponseEntity<?> listShowTime(){
        List<ShowTime> showTimeList = showTimeService.listShowTime();
        return new ResponseEntity<>(showTimeList,HttpStatus.OK);
    }
}
