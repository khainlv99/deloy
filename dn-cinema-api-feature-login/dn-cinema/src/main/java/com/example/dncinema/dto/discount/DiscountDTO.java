package com.example.dncinema.dto.discount;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import javax.validation.constraints.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
public class DiscountDTO implements Validator {
    private Integer idDiscount;
    @NotBlank(message = "Tên khuyến mãi không được để trống")
    @Length(max = 255,message = "Tên khuyến mãi không dài quá 255 từ")
    private String nameDiscount;
    @NotBlank(message = "Hình ảnh không được để trống")
    @Pattern(regexp = "^.{0,}(.png|.jpg|.jpeg)[?](alt=media&token=).{0,}$",message = "Sai định dạng ảnh, phải có dạng đuôi .jpg, .jpeg, .png")
    private String imageDiscount;
    @NotBlank(message = "Ngày bắt đầu không được để trống")
    @DateTimeFormat(fallbackPatterns = "yyyy-MM-dd")
    private String dateStart;
    @NotNull(message = "Ngày kết thúc không được để trống")
    @DateTimeFormat(fallbackPatterns = "yyyy-MM-dd")
    private String dateEnd;
    @NotBlank(message = "Chi tiết khuyến mãi không được để trống")
    private String describeDiscount;
    @NotNull(message = "Phần trăm giảm giá không được để trống")
    @Max(value = 100,message = "Phần trăm giảm giá không được quá 100")
    @DecimalMin(value = "0.0", inclusive = false,message = "Phần trăm giảm giá phải lớn hơn 0")
    private Double percentDiscount;
    public DiscountDTO() {
    }
    public Integer getIdDiscount() {
        return idDiscount;
    }
    public void setIdDiscount(Integer idDiscount) {
        this.idDiscount = idDiscount;
    }
    public String getNameDiscount() {
        return nameDiscount;
    }
    public String getImageDiscount() {
        return imageDiscount;
    }
    public void setImageDiscount(String imageDiscount) {
        this.imageDiscount = imageDiscount;
    }
    public void setNameDiscount(String nameDiscount) {
        this.nameDiscount = nameDiscount;
    }
    public String getDateStart() {
        return dateStart;
    }
    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }
    public String getDateEnd() {
        return dateEnd;
    }
    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }
    public String getDescribeDiscount() {
        return describeDiscount;
    }
    public void setDescribeDiscount(String describeDiscount) {
        this.describeDiscount = describeDiscount;
    }
    public Double getPercentDiscount() {
        return percentDiscount;
    }
    public void setPercentDiscount(Double percentDiscount) {
        this.percentDiscount = percentDiscount;
    }
    @Override
    public boolean supports(Class<?> clazz) {
        return DiscountDTO.class.equals(clazz);
    }
    @Override
    public void validate(Object target, Errors errors) {
        DiscountDTO discountDTO = (DiscountDTO) target;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
        LocalDate startDate = LocalDate.parse(discountDTO.getDateStart(), formatter);
        LocalDate currentDate = LocalDate.now();
        if (startDate.isBefore(currentDate.plusDays(7))) {
            errors.rejectValue("dateStart", "InvalidStartDate", "Ngày bắt đầu phải lớn hơn ngày hiện tại 7 ngày");
        }
        LocalDate endDate = LocalDate.parse(discountDTO.getDateEnd(), formatter);
        if (endDate.isBefore(startDate)) {
            errors.rejectValue("dateEnd", "InvalidEndDate", "Ngày kết thúc phải lớn hơn ngày bắt đầu");
        }
    }
}