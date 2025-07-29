package com.example.dncinema.controller.discount;

import com.example.dncinema.dto.discount.DiscountDTO;
import com.example.dncinema.repository.discount.IDiscountRepository;
import com.example.dncinema.model.Discount;
import com.example.dncinema.service.discount.IDiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class DiscountController {
    @Autowired
    private IDiscountService discountService;

    @Autowired
    private IDiscountRepository discountRepository;

    @GetMapping("/public/discount/{id}")
    public ResponseEntity<Discount> findDiscountById(@PathVariable int id) {
        Discount discount = discountService.findDiscountById(id);
        if (discount == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(discount, HttpStatus.OK);
    }
    @GetMapping("/public/discount-list")
    public ResponseEntity<List<Discount>> showListDiscount() {
        List<Discount> listDiscount = discountService.findAllDiscount();
        if (listDiscount.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(listDiscount, HttpStatus.OK);
    }

    /**
     * Create: TuanLT.
     * Date create: 24/05/2023.
     * @param name     "Dùng để tìm kiếm khuyến mãi theo tên".
     * @param pageable "Dùng để phân trang".
     * @return "Trả về Page hiển thị danh sách khuyến mãi, nếu người dùng tiến hành tìm kiếm thì Page này sẽ hiển thị danh sách được tìm kiếm theo tên".
     */

    @GetMapping("/admin/discount/list")
    @ResponseStatus(HttpStatus.OK)
    public Page<DiscountDTO> showList(@RequestParam(required = false, defaultValue = "") String name,
                                      @PageableDefault(direction = Sort.Direction.DESC, size = 5) Pageable pageable) {
        return discountService.findByName(name, pageable);
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
     * @Return if has errors then return HttpStatus.Not_FOUND else add data into Database
     */
    @PostMapping(value = "admin/discount/create")
    public ResponseEntity<?> createDiscount(@Valid @RequestBody DiscountDTO discountDTO, BindingResult bindingResult) {
        new DiscountDTO().validate(discountDTO,bindingResult);
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        discountService.createDiscount(discountDTO.getNameDiscount(), discountDTO.getDateStart(),
                discountDTO.getDateEnd(), discountDTO.getImageDiscount(),discountDTO.getDescribeDiscount(),  discountDTO.getPercentDiscount());
        return new ResponseEntity<>(HttpStatus.OK);
    }
    /**
     * Create by: HoangPT,
     * Date create : 24/05/2023
     * Function : Find discount information by id
     *
     * @param idDiscount
     */
    @GetMapping("/admin/discount/{idDiscount}")
    public ResponseEntity<Discount> findDiscountByID(@PathVariable("idDiscount") Integer idDiscount) {
        Discount discount = discountService.findDiscountById(idDiscount);
        if (discount == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(discount, HttpStatus.OK);
    }
    /**
     * Create by: HoangPT,
     * Date create : 24/05/2023
     * Function: edit discount data if ID is not found then return HttpStatus.NOT_FOUND,
     * if found ID then edit data in DB and return HttpStatus.OK
     *
     * @Param("idDiscount") Integer idDiscount
     * @Param("nameDiscount") String nameDiscount
     * @Param("dateStart") LocalDate dateStart
     * @Param("dateEnd") LocalDate dateEnd
     * @Param("describeDiscount") String describeDiscount
     * @Param("percentDiscount") String percentDiscount
     */
    @PutMapping("/admin/discount/update/{idDiscount}")
    public ResponseEntity<?> updateDiscount(@Valid @RequestBody DiscountDTO discountDTO, BindingResult bindingResult) {
        new DiscountDTO().validate(discountDTO,bindingResult);
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        Discount discount=discountService.findDiscountById(discountDTO.getIdDiscount());
        if (discount==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        discountService.updateDiscount(discountDTO.getIdDiscount(),discountDTO.getNameDiscount(), discountDTO.getDateStart(),
                discountDTO.getDateEnd(), discountDTO.getImageDiscount(),discountDTO.getDescribeDiscount(), discountDTO.getPercentDiscount());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    
    /**
     * Create: TuanLT
     * Date create: 24/05/2023.
     * @param id "Tìm kiếm id của 1 khuyến mãi và xóa nó".
     */

    @DeleteMapping("/admin/discount/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int id) {
        if (!discountRepository.existsById(id)) {
            return new ResponseEntity<>("ID không tồn tại!", HttpStatus.NOT_FOUND);
        }
        discountService.delete(id);
        return new ResponseEntity<>("Xóa thành công!", HttpStatus.OK);
    }


}
