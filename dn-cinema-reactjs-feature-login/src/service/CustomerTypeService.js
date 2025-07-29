import axios from "axios";

export const findAllCustomerType = async () => {
    try {
        const result = await axios.get("http://localhost:8080/api/employee/customerType")
        console.log(result.data)
        return result.data
    } catch (error) {
        console.log(error)
    }
}
export const customerTypeService = {findAllCustomerType}