import { Button, Box, TextField, Typography } from "@mui/material";

export default function UpdateStatus({
  formData,
  onChange,
  maintenanceStatus,
  updateMaintenance,
}) {
  return (
    <Box>
      {formData.jobs.map((job, jobIndex) => (
        <div
          key={jobIndex}
          style={{
            padding: "1rem",
          }}
        >
          <Typography variant="h6">Job To Update</Typography>

          <TextField
            select
            name={`jobs.${jobIndex}.status`}
            value={job.status}
            onChange={onChange}
            fullWidth
            margin="normal"
            SelectProps={{ native: true }}
          >
            <option value="" disabled>
              select new status
            </option>
            {maintenanceStatus.map((status) => (
              <option key={status} value={status}>
                {status}
              </option>
            ))}
          </TextField>
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

          {job.status === "COMPLETED" && (
            <TextField
              label="Amount"
              name={`jobs.${jobIndex}.amount`}
              value={job.amount}
              onChange={onChange}
              fullWidth
              margin="normal"
              type="number"
              
            />
          )}
          <Button
            variant="contained"
            onClick={() =>
              updateMaintenance(job.status, job.endTime, job.amount)
            }
          >
            update Workshop
          </Button>
        </div>
      ))}
    </Box>
  );
}
