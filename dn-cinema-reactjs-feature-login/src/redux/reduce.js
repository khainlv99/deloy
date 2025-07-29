const initialState = {
  data: null,
  account: null,
};

const apiReducer = (state = initialState, action) => {
  switch (action.type) {
    case "RECEIVE_DATA":
      return { ...state, data: action.payload };
    case "RECEIVE_ACCOUNT":
      return { ...state, account: action.payload };
    default:
      return state;
  }
};

export default apiReducer;
