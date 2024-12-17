import React from "react";
import useVehicle from "./service";
import List from "../../components/list";
import { Button, Typography, Box, Container } from "@mui/material";
import VehicleAdd from "./main";
import Modal from "../../components/modal";
import Dialog from "../../components/dialogue";
export default function Vehicle() {
  const {
    fetchError,
    vehicles,
    handleOpen,
    open,
    handleClose,
    onChange,
    addVehicle,
    columns,
    handleCloseNotif,
    handleOpenNotif,
    openNotif,
    vehicleConditions,
    identityNumber,
    selectedIdentityNumber,
    selectedVehicleCondition,
    formData,
    isUpdating,
    updateVehicle,
    updated,
    openUpdate
  } = useVehicle();
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
          Vehicles
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
          New Vehicle +
        </Button>
        <VehicleAdd
          open={open}
          handleClose={handleClose}
          onChange={onChange}
          addVehicle={addVehicle}
          vehicleConditions={vehicleConditions}
          identityNumber={identityNumber}
          selectedIdentityNumber={selectedIdentityNumber}
          selectedVehicleCondition={selectedVehicleCondition}
          formData={formData}
          isUpdating={isUpdating}
          updateVehicle={updateVehicle}
        />
      </Container>
      <Container sx={{ marginTop: "100px", minWidth: "100%" }}>
        <Typography variant="h5">Vehicles List</Typography>
        <List data={vehicles} columns={columns} openUpdate={openUpdate} />
      </Container>
      <Modal
        open={openNotif}
        handleClose={handleCloseNotif}
        renderContent={
          <Dialog
            message={
              updated
                ? "Vehicle updated successefuly"
                : "Vehicle Added Successefuly"
            }
            messageButton={"Back To Vehicles"}
            handleClose={handleCloseNotif}
          />
        }
        width="30%"
      />
    </Box>
  );
}
