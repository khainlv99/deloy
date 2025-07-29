package com.example.dncinema.repository.discount;

import com.example.dncinema.model.Discount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IDiscountRepository extends JpaRepository<Discount, Integer> {
    /**
     * Author: TuanLT
     * Date: 24/05/2023
     * @param name
     * @param pageable
     * @return
     */

    @Query(value = "SELECT * FROM Discount WHERE name_discount like concat('%',:name,'%') and is_deleted = false", nativeQuery = true)
    Page<Discount> searchName(@Param("name") String name, Pageable pageable);

//    Discount findById(int id);
//    Discount findById(Long id);
    /**
     * Create by: HoangPT,
     * Date create : 24/05/2023
     * Function : Add new discount to database
     *
     * @Param("nameDiscount") String nameDiscount,
     * @Param("dateStart") LocalDate dateStart,
     * @Param("dateEnd") LocalDate dateEnd,
     * @Param("describeDiscount") String describeDiscount,
     * @Param("percentDiscount") Double percentDiscount
     */
    @Transactional
    @Modifying
    @Query(value = "insert into discount(name_discount, date_start,date_end,image_discount, describe_discount, percent_discount) " +
            "values (:nameDiscount,:dateStart,:dateEnd,:imageDiscount,:describeDiscount,:percentDiscount)",
            nativeQuery = true)
    void createDiscount(
            @Param("nameDiscount") String nameDiscount,
            @Param("dateStart") String dateStart,
            @Param("dateEnd") String dateEnd,
            @Param("imageDiscount") String imageDiscount,
            @Param("describeDiscount") String describeDiscount,
            @Param("percentDiscount") Double percentDiscount
    );

    /**
     * Create by: HoangPT,
     * Date create : 24/05/2023
     * Function : Find discount information in database by id
     *
     * @param idDiscount
     */
    @Query(value = "select id_discount, name_discount, date_start,date_end,image_discount, describe_discount, percent_discount,is_deleted " +
            "from discount where id_discount = :idDiscount", nativeQuery = true)
    Discount findDiscountById(int idDiscount);


    /**
     * Create by: HoangPT,
     * Date create : 24/05/2023
     * Function : Edit information a discount in database
     *
     * @Param("idDiscount") String idDiscount
     * @Param("nameDiscount") String nameDiscount
     * @Param("dateStart") LocalDate dateStart
     * @Param("dateEnd") LocalDate dateEnd
     * @Param("describeDiscount") String describeDiscount
     * @Param("percentDiscount") String percentDiscount
     */
    @Modifying
    @Transactional
    @Query(value = "update discount set " +
            "name_discount = :nameDiscount," +
            "date_start = :dateStart," +
            "date_end = :dateEnd," +
            "image_discount=:imageDiscount,"+
            "describe_discount = :describeDiscount," +
            "percent_discount = :percentDiscount" +
            " WHERE id_discount = :idDiscount",
            nativeQuery = true)
    void updateDiscount(
            @Param("idDiscount")Integer idDiscount,
            @Param("nameDiscount") String nameDiscount,
            @Param("dateStart") String dateStart,
            @Param("dateEnd") String dateEnd,
            @Param("imageDiscount") String imageDiscount,
            @Param("describeDiscount") String describeDiscount,
            @Param("percentDiscount") Double percentDiscount
    );
    @Query(value = "SELECT * FROM discount ", nativeQuery = true)
    List<Discount> findAllDiscount();
}
