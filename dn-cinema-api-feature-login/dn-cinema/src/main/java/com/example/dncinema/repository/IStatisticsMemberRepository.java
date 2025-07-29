package com.example.dncinema.repository;

import com.example.dncinema.dto.StatisticsMemberDTO;
import com.example.dncinema.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IStatisticsMemberRepository extends JpaRepository<Customer,Integer> {
    @Query(value = "SELECT\n" +
            "    customer.id_customer AS memberid,\n" +
            "    customer.name_customer AS membername,\n" +
            "    COUNT(ticket.id_ticket) AS totaltickets,\n" +
            "    SUM(ticket.price_after_discount) AS totalrevenue,\n" +
            "    customer.point_customer AS loyaltypoints\n" +
            "FROM \n" +
            "    customer\n" +
            "    JOIN ticket ON customer.id_customer = ticket.id_customer \n" +
            "GROUP BY\n" +
            "    customer.id_customer limit 4",nativeQuery = true)
    List<StatisticsMemberDTO> findCommentSummaryByTitleMember();
    @Query(value = "select * from members where membername like CONCAT('%', :membername, '%')", nativeQuery = true)
    List<StatisticsMemberDTO> findStatisticsDTOByNameMember(@Param("membername") String membername);
}
