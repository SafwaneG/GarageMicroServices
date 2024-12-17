import { configureStore } from "@reduxjs/toolkit";
import LoadingReducer from "./loading/loadingSlice";
import snackbarReducer from "./notification/messageSlice"

const store = configureStore({
  reducer: {
    Laoding: LoadingReducer,
    snackbar:snackbarReducer,
   

  }
});
export default store;
