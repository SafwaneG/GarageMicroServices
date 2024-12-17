import * as React from "react";
import Box from "@mui/material/Box";
import Button from "@mui/material/Button";
import Typography from "@mui/material/Typography";
import Modal from "@mui/material/Modal";

export default function BasicModal({
  open = false,
  handleClose = () => {},
  renderContent,
  width,
}) {
  const style = {
    position: "absolute",
    top: "50%",
    left: "50%",
    transform: "translate(-50%, -50%)",
    width,
    bgcolor: "background.paper",
    borderRadius: 2,
    boxShadow: 24,
    py: 7,
    maxHeight: "80vh", // Limite la hauteur du modal à 80% de la fenêtre
    overflowY: "auto",
  };
  return (
    <div>
      <Modal
        open={open}
        onClose={handleClose}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <Box sx={style}>{renderContent}</Box>
      </Modal>
    </div>
  );
}
