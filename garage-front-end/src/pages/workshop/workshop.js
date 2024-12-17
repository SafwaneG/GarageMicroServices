import React from "react";
import Modal from "../../components/modal";
import Dialog from "../../components/dialogue";
import FormStatus from "../../components/formUpdateStatus";
import {
  Card,
  CardContent,
  Typography,
  Chip,
  Container,
  Grid,
  Box,
  Button,
} from "@mui/material";
import useWorkshop from "./service";
import MainWorkshop from "./main";

const formatDate = (isoString) => {
  const options = {
    weekday: "long",
    year: "numeric",
    month: "long",
    day: "numeric",
    hour: "2-digit",
    minute: "2-digit",
  };
  return new Date(isoString).toLocaleDateString("en-US", options);
};

const statusColors = {
  IN_PROGRESS: "warning",
  PENDING: "default",
  COMPLETED: "success",
  PAUSED: "info",
  SCHEDULED: "primary",
};

const JobCard = ({ job, workshopId, handleOpenStatus }) => {
  return (
    <Card variant="outlined" style={{ marginBottom: "1rem" }}>
      <CardContent>
        <Typography variant="h6" gutterBottom>
          {job.description}
        </Typography>
        <Typography variant="body2">
          <strong>Time:</strong> {formatDate(job.startTime)} -{" "}
          {formatDate(job.endTime)}
        </Typography>
        <Typography variant="body2">
          <strong>Registration:</strong> {job.registrationNumber}
        </Typography>
        <Button
          variant="contained"
          color={statusColors[job.status]}
          style={{ marginTop: "0.5rem", borderRadius: "19px" }}
          onClick={() => handleOpenStatus(job.jobId, workshopId)}
          disabled={job.status === "COMPLETED"}
          sx={{
            "&:disabled": {
              backgroundColor:"green",
              color:"white"
            }
          }}
        >
          {job.status}
        </Button> 
      </CardContent>
    </Card>
  );
};

export default function WorkshopView() {
  const {
    onChange,
    handleOpen,
    handleClose,
    open,
    loading,
    fetchError,
    handleCloseNotif,
    openNotif,
    formData,
    addJob,
    addWorkshop,
    maintenanceStatus,
    registrationNumber,
    workshop,
    updateStatus,
    handleOpenStatus,
    handleCloseStatus,
    openStatus,
    updateMaintenance,
    maintenanceUpdateRequest,
    setMaintenanceUpdateRequest,
    handleCloseAdded,
    openAdded
  } = useWorkshop();

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
        <Typography
          gutterBottom
          variant="h4"
          sx={{ fontWeight: 550, flexGrow: 1 }}
        >
          Workshop Schedule
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
          New Workshop +
        </Button>
        <MainWorkshop
          open={open}
          handleClose={handleClose}
          onChange={onChange}
          addWorkshop={addWorkshop}
          addJob={addJob}
          formData={formData}
          maintenanceStatus={maintenanceStatus}
          registrationNumber={registrationNumber}
        />
      </Container>
      <Container style={{ marginTop: "2rem" }}>
        {loading ? (
          <Typography variant="h6">Loading workshops...</Typography>
        ) : fetchError ? (
          <Typography variant="h6" color="error">
            {fetchError}
          </Typography>
        ) : (
          <>
            <Typography variant="h5" gutterBottom>
              Workshops
            </Typography>
            <Grid container spacing={2}>
              {workshop && workshop.length > 0 ? (
                workshop.map((workshopItem, index) => (
                  <Grid item xs={12} md={6} key={workshopItem.id}>
                    <Card variant="outlined" style={{ marginBottom: "1rem" }}>
                      <CardContent>
                        <Typography variant="h6" gutterBottom>
                          {formatDate(workshopItem.planningDate)}
                        </Typography>
                        {workshopItem.jobs.map((job, jobIndex) => (
                          <JobCard
                            key={job.jobId}
                            job={job}
                            workshopId={workshopItem.id}
                            handleOpenStatus={handleOpenStatus}
                          />
                        ))}
                      </CardContent>
                    </Card>
                  </Grid>
                ))
              ) : (
                <Typography variant="body1">No workshops found.</Typography>
              )}
            </Grid>
          </>
        )}
      </Container>
      <Modal
        open={openAdded}
        handleClose={handleCloseAdded}
        renderContent={
          <Dialog
            message={"Workshop Added Successfully"}
            messageButton={"Back To Workshop"}
            handleClose={handleCloseAdded}
          />
        }
        width="30%"
      />
      <Modal
        open={openStatus}
        handleClose={handleCloseStatus}
        renderContent={
          <FormStatus
            formData={formData}
            maintenanceStatus={maintenanceStatus}
            onChange={onChange}
            updateMaintenance={updateMaintenance}
            maintenanceUpdateRequest={maintenanceUpdateRequest}
            setMaintenanceUpdateRequest={setMaintenanceUpdateRequest}
          />
        }
        width="40%"
      />
      <Modal
        open={openNotif}
        handleClose={handleCloseNotif}
        renderContent={
          <Dialog
            message={"Workshop Updated Successefuly"}
            messageButton={"Back To Workshop"}
            handleClose={handleCloseNotif}
          />
        }
        width="30%"
      />
    </Box>
  );
}
