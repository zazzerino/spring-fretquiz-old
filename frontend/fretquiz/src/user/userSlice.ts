import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { RootState } from "../app/store";
import { User } from "./user";

type UserState = User | null;

const userSlice = createSlice({
  name: 'user',
  initialState: null as UserState,
  reducers: {
    setUser: (_, action: PayloadAction<User>) => action.payload
  }
});

export const selectUser = (state: RootState) => state.user;

export const { setUser } = userSlice.actions;

export default userSlice.reducer;
