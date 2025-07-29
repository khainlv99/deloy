package com.example.dncinema.repository;

import com.example.dncinema.model.TypeFilm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ITypeFilmRepository extends JpaRepository<TypeFilm,Integer> {
    @Query(value = "select * from type_film", nativeQuery = true)
    List<TypeFilm> findAll();
}

