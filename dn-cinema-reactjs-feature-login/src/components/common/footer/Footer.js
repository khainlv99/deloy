import "./index.css";
const Footer = () => {
  return (
    <>
      <>
        <footer className="footer">
          <div className="footer-top">
            <div className="container">
              {/* <div class="footer-brand-wrapper">

    <a href="./index.html" class="logo">
      <img src="./assets/images/logo.png" alt="Filmlane logo" style="height: 80px;width: 150px">
    </a>

    <ul class="footer-list">

     <li>
        <a href="./index.html" class="footer-link">Trang chủ</a>
      </li>

      <li>
        <a href="#" class="footer-link">Phim</a>
      </li> */}
              {/* <li>
        <a href="#" class="footer-link">Góc điện ảnh</a>
      </li> */}
              {/* 
      <li>
        <a href="#" class="footer-link">Sự kiện</a>
      </li>

      <li>
        <a href="#" class="footer-link">Rạp giá vé</a>
      </li> */}
              {/* <li>
        <a href="#" class="footer-link">Hỗ trợ</a>
      </li> 

    </ul>

  </div> */}
              <div className="divider" />
              <div className="quicklink-wrapper">
                <ul className="quicklink-list">
                  <li>
                    <a href="#" className="quicklink-link">
                      Faq
                    </a>
                  </li>
                  <li>
                    <a href="#" className="quicklink-link">
                      Trợ giúp
                    </a>
                  </li>
                  <li>
                    <a href="#" className="quicklink-link">
                      Điều khoản sử dụng
                    </a>
                  </li>
                  <li>
                    <a href="#" className="quicklink-link">
                      Sự riêng tư
                    </a>
                  </li>
                </ul>
                <ul className="social-list">
                  <li>
                    <a href="#" className="social-link">
                      <ion-icon name="logo-facebook" />
                    </a>
                  </li>
                  <li>
                    <a href="#" className="social-link">
                      <ion-icon name="logo-twitter" />
                    </a>
                  </li>
                  <li>
                    <a href="#" className="social-link">
                      <ion-icon name="logo-pinterest" />
                    </a>
                  </li>
                  <li>
                    <a href="#" className="social-link">
                      <ion-icon name="logo-linkedin" />
                    </a>
                  </li>
                </ul>
              </div>
            </div>
          </div>
          <div className="footer-bottom">
            <div className="container">
              <p className="copyright">
                © 2023 <a href="#">Codegym</a>. Đã đăng ký bản quyền
              </p>
              <img
                src="assets\img\home\footer-bottom-img.png"
                alt="Online banking companies logo"
                className="footer-bottom-img"
              />
            </div>
          </div>
        </footer>
        <a href="#top" className="go-top" data-go-top="">
          <ion-icon name="chevron-up" />
        </a>
      </>
    </>
  );
};
export default Footer;
