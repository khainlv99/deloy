package com.example.dncinema.service.discount.impl;

import com.example.dncinema.dto.discount.DiscountDTO;
import com.example.dncinema.model.Discount;
import com.example.dncinema.repository.discount.IDiscountRepository;
import com.example.dncinema.service.discount.IDiscountService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class DiscountService implements IDiscountService {
    @Autowired
    private IDiscountRepository discountRepository;

    /**
     * Author: TuanLT.
     * Date: 24/05/2023.
     *
     * @param name     "Tham số name dùng để tìm kiếm trong danh sách khuyến mãi".
     * @param pageable "Tham số pageable dùng để phân trang".
     * @return "Trả về danh sách khuyến mãi ban đầu có phân trang, nếu người dùng tiến hành tìm kiếm thì sẽ trả về 1 danh sách sau khi người dùng search (có phân trang)."
     */

    @Override
    public Page<DiscountDTO> findByName(String name, Pageable pageable) {
        List<DiscountDTO> discountDTOList = new ArrayList<>();
        Page<Discount> discountPage = discountRepository.searchName(name, pageable);
        DiscountDTO discountDTO;
        for (Discount discount : discountPage) {
            discountDTO = new DiscountDTO();
            BeanUtils.copyProperties(discount, discountDTO);
            discountDTOList.add(discountDTO);
        }
        return new PageImpl<>(discountDTOList, discountPage.getPageable(), discountPage.getTotalElements());
    }

    @Override
    public Discount findById(int id) {
        return discountRepository.findById(id).get();
    }

    @Override
    public void save(Discount discount) {
        discountRepository.save(discount);
    }

    /**
     * Create by: HoangPT,
     * Date create : 24/05/2023
     * Function : Add new discount
     *
     * @Param("nameDiscount") String nameDiscount,
     * @Param("dateStart") LocalDate dateStart,
     * @Param("dateEnd") LocalDate dateEnd,
     * @Param("describeDiscount") String describeDiscount,
     * @Param("percentDiscount") Double percentDiscount
     */
    @Override
    public void createDiscount(String nameDiscount, String dateStart, String dateEnd, String imageDiscount, String describeDiscount, Double percentDiscount) {
        discountRepository.createDiscount(nameDiscount, dateStart, dateEnd, imageDiscount, describeDiscount, percentDiscount);
    }

    /**
     * Create by: HoangPT,
     * Date create : 24/05/2023
     * Function : Find discount information by id
     *
     * @param id
     */
    @Override
    public boolean delete(int id) {
        try {
            discountRepository.deleteById(id);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }

    public Discount findDiscountById(int idDiscount) {
        return discountRepository.findDiscountById(idDiscount);
    }

    /**
     * Create by: HoangPT,
     * Date create : 24/05/2023
     * Function : Edit information a discount in database
     *
     * @Param("idDiscount") Integer idDiscount
     * @Param("nameDiscount") String nameDiscount
     * @Param("dateStart") LocalDate dateStart
     * @Param("dateEnd") LocalDate dateEnd
     * @Param("describeDiscount") String describeDiscount
     * @Param("percentDiscount") String percentDiscount
     */
    @Override
    public void updateDiscount(Integer idDiscount, String nameDiscount, String dateStart, String dateEnd, String imageDiscount, String describeDiscount, Double percentDiscount) {
        discountRepository.updateDiscount(idDiscount, nameDiscount, dateStart, dateEnd, imageDiscount, describeDiscount, percentDiscount);
    }

    @Override
    public List<Discount> findAllDiscount() {
        return discountRepository.findAllDiscount();
    }

}
