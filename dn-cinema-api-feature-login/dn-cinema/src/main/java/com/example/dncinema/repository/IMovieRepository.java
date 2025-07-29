
package com.example.dncinema.repository;

import com.example.dncinema.model.Film;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


import java.util.List;

@Repository
public interface IMovieRepository extends JpaRepository<Film, Integer> {
    @Query(value = "select * from film \n" +
            "join type_film on film.id_type_film = type_film.id_type_film\n" +
            "where name_film like concat('%', :search, '%')", nativeQuery = true)
    Page<Film> findAllFilm(@Param("search") String search,@Param("pageable") Pageable pageable);
    Page<Film> findFilmsByTypeFilm_IdTypeFilm(@Param("id") Integer id, Pageable pageable);


    /**
     * @Author QuynhHTN
     * Date create: 24/05/2023
     * @param id
     * @return findFilmById
     * @Usage_method findById to show detail film
     */
    @Query(value = "select * from film where film.id_film = :id ", nativeQuery = true)
    Film findFilmById(@Param("id") Integer id);

    @Query(value = "select * from film", nativeQuery = true)
    List<Film> findAllListFilm();
    List<Film> findFilmsByDateStartFilmLessThanAndDateEndFilmGreaterThan(LocalDate date, LocalDate date2);
    List<Film> findFilmsByDateStartFilmGreaterThan(LocalDate localDate);
}

