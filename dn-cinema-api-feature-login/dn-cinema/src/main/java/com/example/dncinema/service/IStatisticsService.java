package com.example.dncinema.service;

import com.example.dncinema.dto.StatisticsDTO;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IStatisticsService {
    List<StatisticsDTO> findCommentSummaryByTitle();
    List<StatisticsDTO> findStatisticsDTOByNameFilm(@Param("namefilm") String namefilm);
}
