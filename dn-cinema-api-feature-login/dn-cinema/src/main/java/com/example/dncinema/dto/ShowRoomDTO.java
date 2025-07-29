package com.example.dncinema.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

public class ShowRoomDTO {

    private Integer idShowRoom;

    @NotBlank(message = "Trường này không được để trống")
    @Size(max = 10, message = "Tên phòng chiếu không dài quá 10 ký tự")
    private String nameShowRoom;

    @NotNull
    @Min(value = 1,message = "Số lượng ghế không được nhỏ hơn 0 ")
    @Max(value = 50,message = "Số lượng ghế không được lớn hơn 50 ")
    private Integer quantitySeat;

    public ShowRoomDTO() {
    }

    public ShowRoomDTO(Integer idShowRoom, String nameShowRoom, Integer quantitySeat) {
        this.idShowRoom = idShowRoom;
        this.nameShowRoom = nameShowRoom;
        this.quantitySeat = quantitySeat;
    }

    public Integer getIdShowRoom() {
        return idShowRoom;
    }

    public void setIdShowRoom(Integer idShowRoom) {
        this.idShowRoom = idShowRoom;
    }

    public String getNameShowRoom() {
        return nameShowRoom;
    }

    public void setNameShowRoom(String nameShowRoom) {
        this.nameShowRoom = nameShowRoom;
    }

    public Integer getQuantitySeat() {
        return quantitySeat;
    }

    public void setQuantitySeat(Integer quantitySeat) {
        this.quantitySeat = quantitySeat;
    }
}
