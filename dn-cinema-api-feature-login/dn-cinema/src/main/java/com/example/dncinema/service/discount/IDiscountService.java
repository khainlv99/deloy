package com.example.dncinema.service.discount;

import com.example.dncinema.dto.discount.DiscountDTO;
import com.example.dncinema.model.Discount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface IDiscountService {
    Page<DiscountDTO> findByName(String name, Pageable pageable);

    Discount findById(int id);

    List<Discount> findAllDiscount();

    void save(Discount discount);
//    void delete(Long id);
    /**
     * Create by: HoangPT,
     * Date create : 24/05/2023
     * Function : Add new discount
     *
     * @Param nameDiscount,
     * @Param dateStart,
     * @Param dateEnd,
     * @Param describeDiscount,
     * @Param percentDiscount
     */
    void createDiscount(String nameDiscount, String dateStart, String dateEnd,String img, String describeDiscount,
                        Double percentDiscount);
    /**
     * Create by: HoangPT,
     * Date create : 24/05/2023
     * Function : Find discount information by id
     *
     * @param idDiscount
     */

    Discount findDiscountById(int idDiscount);

    boolean delete(int id);
    /**
     * Create by: HoangPT,
     * Date create : 24/05/2023
     * Function : Edit information a discount
     *
     * @Param Integer idDiscount
     * @Param String nameDiscount
     * @Param LocalDate dateStart
     * @Param LocalDate dateEnd
     * @Param String describeDiscount
     * @Param Double percentDiscount
     */

    void updateDiscount(Integer idDiscount, String nameDiscount, String dateStart, String dateEnd, String img,
                        String describeDiscount, Double percentDiscount);
}
