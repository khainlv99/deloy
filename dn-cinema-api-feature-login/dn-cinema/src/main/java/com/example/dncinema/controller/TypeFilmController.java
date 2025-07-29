package com.example.dncinema.controller;

import com.example.dncinema.model.TypeFilm;
import com.example.dncinema.repository.ITypeFilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/public/typeFilm")
@CrossOrigin("*")
public class TypeFilmController {
    @Autowired
    private ITypeFilmRepository iTypeFilmRepository;

    @GetMapping("")
    public ResponseEntity<?> findAllTypeFilm(){
        List<TypeFilm> typeFilmList = iTypeFilmRepository.findAll();
        return new ResponseEntity<>(typeFilmList,HttpStatus.OK);
    }
}
