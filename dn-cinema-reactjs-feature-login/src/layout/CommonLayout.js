import { Outlet } from "react-router-dom";
import Footer from "../components/common/footer/Footer";
const CommonLayout = () => {
    return (
        <>
            {/*<Header/>*/}
            <div  className="page-container" >
                <Outlet/>
            </div>
            {/*<Footer/>*/}
        </>
    )
};

export default CommonLayout;
