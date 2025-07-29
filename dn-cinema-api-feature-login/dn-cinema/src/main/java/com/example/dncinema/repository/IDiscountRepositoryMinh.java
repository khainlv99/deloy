package com.example.dncinema.repository;

import com.example.dncinema.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IDiscountRepositoryMinh extends JpaRepository<Discount,Integer> {
    @Query(value = "select * from discount where name_discount=:name",nativeQuery = true)
    Discount findByNameDiscount(@Param("name") String name);

    @Query(value = "select * from discount where id_discount=:id",nativeQuery = true)
    Discount getByIdDiscount(@Param("id") Integer id);
}
