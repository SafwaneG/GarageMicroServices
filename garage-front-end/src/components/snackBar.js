import * as React from "react";
import Snackbar from "@mui/material/Snackbar";
import MuiAlert from "@mui/material/Alert";
import { useSelector, useDispatch } from "react-redux";
import { cleanSnackBar } from "../store/notification/messageSlice"; 
const Alert = React.forwardRef(function Alert(props, ref) {
  return <MuiAlert elevation={6} ref={ref} variant="filled" {...props} />;
});

export default function SnackbarComponent() {
  const dispatch = useDispatch();
  const { open, message, type } = useSelector((state) => state.snackbar);
  
  const handleClose = () => {
    dispatch(cleanSnackBar());
  };

  React.useEffect(() => {
    let timer;

    if (open) {
      timer = setTimeout(() => {
        handleClose(); 
      }, 3000);
    }

    return () => clearTimeout(timer); 
  }, [open, dispatch]);

  return (
    <Snackbar
      open={open}
      onClose={handleClose}
      anchorOrigin={{ vertical: "top", horizontal: "center" }}
    >
      <Alert onClose={handleClose} severity={type} sx={{ width: "100%" }}>
        {message}
      </Alert>
    </Snackbar>
  );
}
