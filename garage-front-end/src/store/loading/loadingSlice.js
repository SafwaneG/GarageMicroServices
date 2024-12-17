import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  isLoading: false,
};


const loadingSlice = createSlice({
  name: "Laoding",
  initialState,
  reducers: {
    updated: (state , actions) => {
      state.isLoading=actions.payload;
    },
  },
  
});
export default loadingSlice.reducer;
export const { updated } = loadingSlice.actions;
