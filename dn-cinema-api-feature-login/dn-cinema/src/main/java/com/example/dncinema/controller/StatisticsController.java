package com.example.dncinema.controller;

import com.example.dncinema.dto.StatisticsDTO;
import com.example.dncinema.dto.StatisticsMemberDTO;
import com.example.dncinema.service.IStatisticsMemberService;
import com.example.dncinema.service.IStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/statistics")
@CrossOrigin("*")
public class StatisticsController {
    @Autowired
    private IStatisticsService iStatisticsService;
    @Autowired
    private IStatisticsMemberService statisticsMemberService;

    /**
     * @author KhaiNLV
     * @body findAllStatisticFilmDTO
     * @return return new ResponseEntity<>(statisticsDTO,HttpStatus.OK)
     * Phương thức sử dụng để thống kê phim
     * Kết quả trả về là 1 object bao gồm: message thành công khi hiển thị danh sách thành công hoặc thất bại
     */

    @GetMapping("/film")
    public ResponseEntity<?> findAllStatisticDTO(){
        List<StatisticsDTO> statisticsDTO = iStatisticsService.findCommentSummaryByTitle();
        if (statisticsDTO == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(statisticsDTO,HttpStatus.OK);
    }


    /**
     * @author KhaiNLV
     * @body findAllStatisticMemberDTO
     * @return ResponseEntity<>(list,HttpStatus.OK)
     * Phương thức sử dụng để thống kê thành viên
     * Kết quả trả về là 1 object bao gồm: message thành công khi hiển thị danh sách thành công hoặc thất bại
     */

    @GetMapping("/member")

    public ResponseEntity<?> findAllStatisticMemberDTO(){
        List<StatisticsMemberDTO> list = statisticsMemberService.findCommentSummaryByTitleMember();
        if (list.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    /**
     * @author KhaiNLV
     * @body seachByName
     * @return return new ResponseEntity<>(statisticsDTO, HttpStatus.OK)
     * Phương thức sử dụng để tìm kiếm tên phim
     * Kết quả trả về là 1 object bao gồm: message thành công khi tìm kiếm thành công hoặc thất bại
     */
    @GetMapping("/film/search")
    public ResponseEntity<?> searchByName(@RequestParam(value = "name",defaultValue = "") String name) {
        List<StatisticsDTO> statisticsDTO = iStatisticsService.findStatisticsDTOByNameFilm(name);
        if (statisticsDTO == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(statisticsDTO, HttpStatus.OK);
    }
    /**
     * @author KhaiNLV
     * @body seachByNameMember
     * @return return new ResponseEntity<>(statisticsDTO, HttpStatus.OK)
     * Phương thức sử dụng để tìm kiếm tên thành viên
     * Kết quả trả về là 1 object bao gồm: message thành công khi tìm kiếm thành công hoặc thất bại
     */
    @GetMapping("/member/search")
    public ResponseEntity<?> searchByNameMember(@RequestParam(value = "name",defaultValue = "") String nameMember) {

        List<StatisticsMemberDTO> statisticsDTO = statisticsMemberService.findStatisticsDTOByNameMember(nameMember);
        if (statisticsDTO == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(statisticsDTO, HttpStatus.OK);
    }

}
