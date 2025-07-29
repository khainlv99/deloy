import {useEffect} from "react";

const Payment = (props) => {
    const {filmData, listSelectingData} = props;
    useEffect(() => {
        console.log(listSelectingData)
    }, [listSelectingData]);
    return(
        <>
            <div className="font-weight-normal mb-1">Tên Phim: {filmData?.film.nameFilm}</div>
            <div className="font-weight-normal mb-1">Ngày chiếu: {filmData?.showTime.showDate}</div>
            <div className="font-weight-normal mb-1">Lịch chiếu phim: {filmData.showTime.showTime}</div>
            <div className="font-weight-normal mb-1">Thời lượng: {filmData.film.timeFilm} phút</div>
            <div className="font-weight-normal mb-1">List: {listSelectingData} ghế</div>
        </>
    );

};
export default Payment;