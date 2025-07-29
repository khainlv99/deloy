package com.example.dncinema.dto;

import java.time.LocalDate;

public interface IListTicketDTO {
    Integer getIdTicket();
    Boolean getDelete();

    Boolean getStatusTicket();

    Integer getIdCustomer();

    String getNameCustomer();

    String getPhone();

    String getIdentityCard();

    String getNameFilm();

    LocalDate getShowDate();

    String getShowTime();

}
