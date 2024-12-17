import React from "react";
import useClient from "./service";
import List from "../../components/list";
import { Button, Typography, Box, Container } from "@mui/material";
import ClientAdd from "./main";
import Modal from "../../components/modal";
import Dialog from "../../components/dialogue";
export default function Client() {
  const {
    fetchError,
    clients,
    handleOpen,
    open,
    handleClose,
    onChange,
    addClient,
    formData,
    columns,
    handleCloseNotif,
    handleOpenNotif,
    openNotif,
    updateClient,
    isUpdating,
    openUpdate,
    updated,
  } = useClient();
  const list = [];

  return (
    <Box>
      <Container
        sx={{
          display: "flex",
          alignItems: "center",
          margin: 0,
          justifyContent: "space-between",
          minWidth: "100%",
        }}
      >
        <Typography variant="h4" sx={{ fontWeight: 550, flexGrow: 1 }}>
          Clients
        </Typography>
        <Button
          sx={{
            backgroundColor: "#f44336",
            color: "white",
            textTransform: "none",
            padding: "8px 16px",
            borderRadius: "4px",
            "&:hover": {
              backgroundColor: "#d32f2f",
            },
          }}
          onClick={handleOpen}
        >
          New Client +
        </Button>
        <ClientAdd
          open={open}
          handleClose={handleClose}
          onChange={onChange}
          addClient={addClient}
          formData={formData}
          isUpdating={isUpdating}
          updateClient={updateClient}
        />
      </Container>
      <Container sx={{ marginTop: "100px", minWidth: "100%" }}>
        <Typography variant="h5">Clients List</Typography>
        <List data={clients} columns={columns} openUpdate={openUpdate} />
      </Container>
      <Modal
        open={openNotif}
        handleClose={handleCloseNotif}
        renderContent={
          <Dialog
            message={
              updated
                ? "Client updated successefuly"
                : "Client Added Successefuly"
            }
            messageButton={"Back To Client"}
            handleClose={handleCloseNotif}
          />
        }
        width="30%"
      />
    </Box>
  );
}
