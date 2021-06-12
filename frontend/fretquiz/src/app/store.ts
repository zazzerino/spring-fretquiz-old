import { configureStore, ThunkAction, Action } from '@reduxjs/toolkit';
import user from '../user/userSlice';

export const store = configureStore({
  reducer: {
    user,
  },
});

store.subscribe(() => console.log(store.getState()));

export type AppDispatch = typeof store.dispatch;
export type RootState = ReturnType<typeof store.getState>;
export type AppThunk<ReturnType = void> = ThunkAction<
  ReturnType,
  RootState,
  unknown,
  Action<string>
>;
