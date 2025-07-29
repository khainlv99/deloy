import React from "react";
import "./index.css";
import ListFilm from "../../components/film/List";
import Slider from "react-slick";
import Header from "../../components/common/header/Header";
import Footer from "../../components/common/footer/Footer";
import { useEffect } from "react";

const Home = () => {
  const settings = {
    dots: false,
    infinite: true,
    speed: 500,
    slidesToShow: 1,
    slidesToScroll: 1,
    autoplay: true,
    autoplaySpeed: 2000,
    arrows: false,
    responsive: [
      {
        breakpoint: 1024,
        settings: {
          slidesToShow: 1,
          slidesToScroll: 1,
          infinite: true,
          dots: false,
        },
      },
      {
        breakpoint: 768,
        settings: {
          slidesToShow: 1,
          slidesToScroll: 1,
          dots: false,
        },
      },
    ],
  };
  useEffect(() => {
    document.title = "Trang chủ";
  }, []);
  return (
    <>
      <Header />
      <main>
        <article>
          <section>
            <Slider {...settings}>
              <div>
                <img
                  src="https://static.vieon.vn/vieplay-image/carousel_web_v4/2020/12/25/jf6thy2g_1920x1080-robinhood.jpg"
                  alt="slide1"
                  width={"100%"}
                  height={"800px"}
                />
              </div>
              <div>
                <img
                  src="https://static2.vieon.vn/vieplay-image/carousel_web_v4/2022/03/24/rb6jtzt3_1920x1080-fast9.jpg"
                  alt="slide2"
                  width={"100%"}
                  height={"800px"}
                />
              </div>
              <div>
                <img
                  src="http://designercomvn.s3.ap-southeast-1.amazonaws.com/wp-content/uploads/2017/07/26020157/poster-phim-kinh-di.jpg"
                  alt="slide3"
                  height={"800px"}
                  width={"100%"}
                />
              </div>
            </Slider>
          </section>
          <ListFilm />
        </article>
      </main>
      <Footer />
    </>
  );
};
export default Home;
