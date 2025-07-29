package com.example.dncinema.service.statistics;

import com.example.dncinema.dto.StatisticsDTO;
import com.example.dncinema.repository.IStatisticsRepository;
import com.example.dncinema.service.IStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticsService implements IStatisticsService {

    @Autowired
    private IStatisticsRepository repository;
    @Override
    public List<StatisticsDTO> findCommentSummaryByTitle() {
        return repository.findCommentSummaryByTitle();
    }

    @Override
    public List<StatisticsDTO> findStatisticsDTOByNameFilm(String namefilm) {
        return repository.findStatisticsDTOByNameFilm(namefilm);
    }

}
