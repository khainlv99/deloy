import { useEffect, useRef, useState } from "react";
import { Bar } from "react-chartjs-2";
import faker from "faker";
import axios from "axios";
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend,
} from "chart.js";
import Header from "../common/header/Header";
import Footer from "../common/footer/Footer";

ChartJS.register(
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend
);

export function StatisticalMember() {
  const [chartData, setChartData] = useState(null);
  const [keyword, setKeyword] = useState("");
  const options = {
    responsive: true,
    plugins: {
      legend: {
        position: "top",
      },
      title: {
        display: true,
        text: "Thống kê",
        color: "#007bff",
      },
      canvas: {
        height: 100,
      },
    },
  };

  const labels = chartData ? chartData.map((charts) => charts.memberName) : [];
  console.log(labels);

  const data = {
    labels,
    datasets: [
      {
        label: "Tổng số vé đã đặt của mỗi thành viên",
        data: chartData ? chartData.map((charts) => charts.totalTickets) : [],
        backgroundColor: "rgba(193, 43, 120, 0.8)",
      },
    ],
  };
  const auth = localStorage.getItem("token");
  const headers = {
    Authorization: "Bearer " + auth,
  };
  useEffect(() => {
    document.title = "Thống kê khách hàng";
    const fetchData = async () => {
      await axios
        .get("http://localhost:8080/api/admin/statistics/member", { headers })
        .then((e) => {
          setChartData(e.data);
        })
        .catch((e) => {
          console.log(e);
        });
    };
    fetchData();
  }, []);
  console.log(chartData);
  const handleSearch = async () => {
    try {
      const response = await axios.get(
        `http://localhost:8080/api/admin/statistics/member/search?name=${keyword}`,
        { headers }
      );
      setChartData(response.data);
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <>
      <Header />
      <div style={{ margin: "150px 0" }}>
        <Bar
          options={options}
          data={data}
          style={{ height: "300px", margin: "0 auto", width: "1000px" }}
        />
      </div>
      <div className="row mx-0">
        <div className="container mx-auto my-5 col-8" style={{ width: "80%" }}>
          <div style={{ boxShadow: "1px 3px 10px 5px rgba(0, 0, 0, 0.2)" }}>
            <div style={{ marginBottom: 20 }}>
              <h2
                className="d-flex justify-content-center"
                style={{
                  padding: 16,
                  backgroundColor: "rgb(242, 107, 56)",
                  color: "#fff",
                  fontSize: "24px",
                }}
              >
                THỐNG KÊ SỐ LƯỢNG THÀNH VIÊN ĐÃ ĐẶT VÉ
              </h2>
            </div>
            <div className="row">
              <div className="col-2 col-md-6" />
              <div className="col-10 col-md-6 p-0 d-flex justify-content-center gap-2">
                <div className="col-md-9">
                  <input
                    type="text"
                    value={keyword}
                    onChange={(e) => setKeyword(e.target.value)}
                    style={{ width: "100%", height: "36px" }}
                    name="search"
                    className="form-control mx-2"
                    placeholder="Tìm kiếm theo mã vé, tên phim..."
                  />
                </div>
                <div className="col-md-3">
                  <button
                    onClick={handleSearch}
                    style={{
                      background: "rgb(242, 107, 56)",
                      color: "#fff",
                      border: "none",
                    }}
                    className="btn btn-outline-success"
                  >
                    <svg
                      xmlns="http://www.w3.org/2000/svg"
                      width={16}
                      height={16}
                      fill="currentColor"
                      className="bi bi-search"
                      viewBox="0 0 16 16"
                    >
                      <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z" />
                    </svg>
                  </button>
                </div>
              </div>
            </div>
            <div style={{ marginTop: 20 }}>
              <div className="row">
                <div className="col-md-12">
                  <div className="d-flex justify-content-center">
                    <table
                      className="table table-striped table-hover"
                      style={{ width: "85%" }}
                    >
                      <thead>
                        <tr>
                          <th
                            style={{
                              fontSize: "14px",
                              padding: "10px",
                              backgroundColor: "#F3F3F3",
                            }}
                          >
                            STT
                          </th>
                          <th
                            style={{
                              fontSize: "14px",
                              padding: "10px",
                              backgroundColor: "#F3F3F3",
                            }}
                          >
                            Mã thành viên
                          </th>
                          <th
                            style={{
                              fontSize: "14px",
                              padding: "10px",
                              backgroundColor: "#F3F3F3",
                            }}
                          >
                            Tên thành viên
                          </th>
                          <th
                            style={{
                              fontSize: "14px",
                              padding: "10px",
                              backgroundColor: "#F3F3F3",
                            }}
                          >
                            Số lượng vé bán được
                          </th>
                          <th
                            style={{
                              fontSize: "14px",
                              padding: "10px",
                              backgroundColor: "#F3F3F3",
                            }}
                          >
                            Tổng tiền
                          </th>
                          <th
                            style={{
                              fontSize: "14px",
                              padding: "10px",
                              backgroundColor: "#F3F3F3",
                            }}
                          >
                            Điểm tích luỹ
                          </th>
                        </tr>
                      </thead>
                      <tbody>
                        {chartData &&
                          chartData.map((chart, index) => {
                            return (
                              <tr>
                                <td
                                  style={{ fontSize: "14px", padding: "10px" }}
                                >
                                  {index + 1}
                                </td>
                                <td
                                  style={{ fontSize: "14px", padding: "10px" }}
                                >
                                  {chart.memberId}
                                </td>
                                <td
                                  style={{ fontSize: "14px", padding: "10px" }}
                                >
                                  {chart.memberName}
                                </td>
                                <td
                                  style={{ fontSize: "14px", padding: "10px" }}
                                >
                                  {chart.totalTickets}
                                </td>
                                <td
                                  style={{ fontSize: "14px", padding: "10px" }}
                                >
                                  {chart.totalRevenue}
                                </td>
                                <td
                                  style={{ fontSize: "14px", padding: "10px" }}
                                >
                                  {chart.loyaltyPoints}
                                </td>
                              </tr>
                            );
                          })}
                      </tbody>
                    </table>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <Footer />
    </>
  );
}
