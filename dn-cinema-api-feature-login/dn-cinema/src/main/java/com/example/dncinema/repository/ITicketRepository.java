package com.example.dncinema.repository;

import com.example.dncinema.dto.IListTicketDTO;
import com.example.dncinema.model.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ITicketRepository extends JpaRepository<Ticket, Integer> {
//    @Query(value = "select * from ticket " +
//            "inner join seat s on ticket.id_seat = s.id_seat" +
//            " inner join show_time st on s.id_seat = st.id_seat " +
//            "inner join film f on st.id_show_time = f.id_show_time " +
//            "inner join customer c on ticket.id_customer = c.id_customer" +
//            " inner join employee e on ticket.id_employee = e.id_employee " +
//            "inner join discount d on ticket.id_discount = d.id_discount" +
//            " where id_ticket=:id", nativeQuery = true)
//    Ticket findTicketById(@Param("id") Integer id);

    /**
     * @Author QuynhHTN
     * Date create: 24/05/2023
     * @Param id
     * @Return new ResponseEntity<>
     * @Usage_method findById to show detail ticket
     */
    @Query(value = "select * from ticket where id_ticket=:id", nativeQuery = true)
    Ticket findTicketById(@Param("id") Integer id);

    /**
     * @Author QuynhHTN
     * Date create: 24/05/2023
     * @Param ticketDetailDTO
     * @Param bindingResult
     * @Param id
     * @Return new ResponseEntity
     * @Usage_method use method update ticket when customers change ticket
     */
    @Modifying
    @Transactional
    @Query(value = "update ticket set id_seat = :id_seat" +
            " where id_ticket=:id", nativeQuery = true)
    void updateTicket(@Param("id_seat") Integer idSeat);

    /**
     * @Author QuynhHTN
     * Date create: 24/05/2023
     * @Param id
     * @Return new ResponseEntity<>
     * @Usage_method use the delete ticket method to cancel the ticket
     */
    @Modifying
    @Transactional
    @Query(value = "update ticket set is_delete = true where id_ticket = :id_ticket", nativeQuery = true)
    void cancelTicket(@Param("id_ticket") Integer id);

    @Query(value = "SELECT t.id_ticket as idTicket, t.is_delete as isDelete, t.status_ticket as statusTicket, c.id_customer as idCustomer, c.name_customer as nameCustomer, c.phone as phone, c.identity_card as identityCard, f.name_film as nameFilm, st.show_date as showDate, st.show_time as showTime\n" +
            "FROM ticket t\n" +
            "JOIN customer c ON t.id_customer = c.id_customer\n" +
            "JOIN seat s ON t.id_seat = s.id_seat\n" +
            "JOIN show_time st ON s.id_show_time = st.id_show_time\n" +
            "JOIN film f ON st.id_film = f.id_film\n" +
            "WHERE (c.name_customer LIKE CONCAT('%', :search, '%')\n" +
            "OR c.phone LIKE CONCAT('%', :search, '%')\n" +
            "OR c.identity_card LIKE CONCAT('%', :search, '%')\n" +
            "OR f.name_film LIKE CONCAT('%', :search, '%'))\n" +
            "AND t.is_delete = false\n" +
            "ORDER BY t.id_ticket",
            countQuery = "SELECT count(*)\n" +
                    "FROM ticket t\n" +
                    "JOIN customer c ON t.id_customer = c.id_customer\n" +
                    "JOIN seat s ON t.id_seat = s.id_seat\n" +
                    "JOIN show_time st ON s.id_show_time = st.id_show_time\n" +
                    "JOIN film f ON st.id_film = f.id_film\n" +
                    "WHERE (c.name_customer LIKE CONCAT('%', :search, '%')\n" +
                    "OR c.phone LIKE CONCAT('%', :search, '%')\n" +
                    "OR c.identity_card LIKE CONCAT('%', :search, '%')\n" +
                    "OR f.name_film LIKE CONCAT('%', :search, '%'))\n" +
                    "AND t.is_delete = false\n" +
                    "ORDER BY t.id_ticket", nativeQuery = true)
    Page<IListTicketDTO> findAllTicket(Pageable pageable, @Param("search") String search);
}
