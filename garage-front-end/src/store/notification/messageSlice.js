import { createSlice } from "@reduxjs/toolkit";

const snackbarSlice = createSlice({
  name: "snackbar",
  initialState: {
    open: false,
    message: "",
    type: "",
  },
  reducers: {
    showSnackbar: (state, action) => {
      state.open = action.payload.open;
      state.message = action.payload.message;
      state.type = action.payload.type;
    },
    cleanSnackBar: (state) => {
      state.message = "";
      state.open = false;
      state.type = "";
    },
  },
});

export const { showSnackbar, cleanSnackBar } = snackbarSlice.actions;
export default snackbarSlice.reducer;
