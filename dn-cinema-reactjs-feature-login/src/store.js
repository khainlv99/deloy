import { createStore, combineReducers } from "redux";
import apiReducer from "./redux/reduce";

const rootReducer = combineReducers({
  api: apiReducer,
});

export const store = createStore(rootReducer);
