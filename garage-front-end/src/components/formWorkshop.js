import React, { useState } from "react";
import {
  Container,
  Typography,
  Grid,
  TextField,
  Button,
  Box,
} from "@mui/material";

const Workshop = ({
  onChange,
  formData,
  addJob,
  maintenanceStatus,
  addWorkshop,
  registrationNumber,
  maintenanceUpdateRequest,
  setMaintenanceUpdateRequest,
}) => {
  return (
    <Container>
      <Typography variant="h4">Workshop Overview</Typography>

      <TextField
        label="Planning Date"
        name="planningDate"
        value={formData.planningDate}
        onChange={onChange}
        fullWidth
        margin="normal"
        type="date"
        InputLabelProps={{ shrink: true }}
      />

      {formData.jobs.map((job, jobIndex) => (
        <div
          key={jobIndex}
          style={{
            marginBottom: "1rem",
            border: "1px solid #ddd",
            padding: "1rem",
            borderRadius: "8px",
          }}
        >
          <Typography variant="h6">Job {jobIndex + 1}</Typography>

          <Box sx={{ display: "flex", gap: "0.5rem" }}>
            <TextField
              label="Start Time"
              name={`jobs.${jobIndex}.startTime`}
              value={job.startTime}
              onChange={onChange}
              fullWidth
              margin="normal"
              type="date"
              InputLabelProps={{ shrink: true }}
            />
            <TextField
              label="End Time"
              name={`jobs.${jobIndex}.endTime`}
              value={job.endTime}
              onChange={onChange}
              fullWidth
              margin="normal"
              type="date"
              InputLabelProps={{ shrink: true }}
            />
          </Box>

          <Box sx={{ display: "flex", gap: "0.5rem" }}>
            <TextField
              select
              name={`jobs.${jobIndex}.registrationNumber`}
              value={job.registrationNumber || ""}
              onChange={onChange}
              fullWidth
              margin="normal"
              SelectProps={{ native: true }}
            >
              <option value="" disabled>
                Registration Number
              </option>
              {registrationNumber.map((regNum) => (
                <option key={regNum} value={regNum}>
                  {regNum}
                </option>
              ))}
            </TextField>
          </Box>

          <TextField
            label="Description"
            name={`jobs.${jobIndex}.description`}
            value={job.description}
            onChange={onChange}
            fullWidth
            margin="normal"
          />
        </div>
      ))}

      <Button variant="contained" color="primary" onClick={addJob} sx={{mr:2}}>
        Add Job
      </Button>

      <Button variant="contained" color="secondary" onClick={addWorkshop}>
        Save Workshop
      </Button>
    </Container>
  );
};

export default Workshop;
