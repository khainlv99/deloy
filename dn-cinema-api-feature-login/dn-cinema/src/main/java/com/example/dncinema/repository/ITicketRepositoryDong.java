package com.example.dncinema.repository;

import com.example.dncinema.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ITicketRepositoryDong extends JpaRepository<Ticket,Integer> {

    Optional<Ticket> findById(Integer id);

    void deleteById(Integer id);
}
