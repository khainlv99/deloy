package com.example.dncinema.dto;

public class SeatDTO {
    private Integer idSeat;
    private String nameSeat;
    private StatusSeatDTO seatDTO;
    private TypeSeatDTO typeSeatDTO;
    private ShowRoomDTO showRoomDTO;

    public SeatDTO() {
    }

    public Integer getIdSeat() {
        return idSeat;
    }

    public void setIdSeat(Integer idSeat) {
        this.idSeat = idSeat;
    }

    public String getNameSeat() {
        return nameSeat;
    }

    public void setNameSeat(String nameSeat) {
        this.nameSeat = nameSeat;
    }

    public StatusSeatDTO getSeatDTO() {
        return seatDTO;
    }

    public void setSeatDTO(StatusSeatDTO seatDTO) {
        this.seatDTO = seatDTO;
    }

    public TypeSeatDTO getTypeSeatDTO() {
        return typeSeatDTO;
    }

    public void setTypeSeatDTO(TypeSeatDTO typeSeatDTO) {
        this.typeSeatDTO = typeSeatDTO;
    }

    public ShowRoomDTO getShowRoomDTO() {
        return showRoomDTO;
    }

    public void setShowRoomDTO(ShowRoomDTO showRoomDTO) {
        this.showRoomDTO = showRoomDTO;
    }
}
