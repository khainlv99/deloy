package com.example.dncinema.service;

import com.example.dncinema.dto.StatisticsMemberDTO;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IStatisticsMemberService {
    List<StatisticsMemberDTO> findCommentSummaryByTitleMember();
    List<StatisticsMemberDTO> findStatisticsDTOByNameMember(@Param("membername") String membername);
}
