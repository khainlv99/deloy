package com.example.dncinema.service.statistics;

import com.example.dncinema.dto.StatisticsMemberDTO;
import com.example.dncinema.repository.IStatisticsMemberRepository;
import com.example.dncinema.service.IStatisticsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticsMemberService implements IStatisticsMemberService {
    @Autowired
    private IStatisticsMemberRepository statisticsMemberRepository;
    @Override
    public List<StatisticsMemberDTO> findCommentSummaryByTitleMember() {
        return statisticsMemberRepository.findCommentSummaryByTitleMember();
    }

    @Override
    public List<StatisticsMemberDTO> findStatisticsDTOByNameMember(String membername) {
        return statisticsMemberRepository.findStatisticsDTOByNameMember(membername);
    }
}
