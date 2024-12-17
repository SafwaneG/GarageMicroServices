import {
  Button,
  Box,
  TextField,
  Typography,
  FormControl,
  InputLabel,
  Select,
  MenuItem,
} from "@mui/material";

export default function AddNewClient({
  onChange,
  addVehicle,
  vehicleConditions,
  identityNumber,
  selectedIdentityNumber,
  selectedVehicleCondition,
  formData,
  isUpdating,
  updateVehicle,
}) {
  return (
    <Box
      sx={{
        mx: 4,
        display: "flex",
        flexDirection: "column",
        alignItems: "center",
      }}
    >
      <Typography component="h1" variant="h5">
        {isUpdating ? "Update Vehicle" : "Add Vehicle"}
      </Typography>
      <Box noValidate sx={{ mt: 1 }}>
        <FormControl fullWidth margin="normal" required>
          <InputLabel id="ownerIdentityNumber-label">
            Owner Identity Number
          </InputLabel>
          <Select
            labelId="ownerIdentityNumber-label"
            id="ownerIdentityNumber"
            name="ownerIdentityNumber"
            value={isUpdating ? formData.ownerId : selectedIdentityNumber} // Ensure this is a single selected value, not an array
            onChange={onChange}
            label="Owner Identity Number"
            disabled={isUpdating}
          >
            {identityNumber.map((identity) => (
              <MenuItem key={identity} value={identity}>
                {identity} {/* Display the identity number */}
              </MenuItem>
            ))}
          </Select>
        </FormControl>

        <Box sx={{ display: "flex", gap: "0.5rem" }}>
          <TextField
            margin="normal"
            required
            id="chassisNumber"
            label="Chassis Number"
            name="chassisNumber"
            fullWidth
            onChange={onChange}
            value={formData?.chassisNumber}
            disabled={isUpdating}
          />
          <TextField
            margin="normal"
            required
            id="registrationNumber"
            label="Registration Number"
            name="registrationNumber"
            fullWidth
            onChange={onChange}
            value={formData?.registrationNumber}
            disabled={isUpdating}
          />
        </Box>

        <Box sx={{ display: "flex", gap: "0.5rem" }}>
          <TextField
            margin="normal"
            required
            id="brand"
            label="Brand"
            name="brand"
            type="string"
            fullWidth
            onChange={onChange}
            value={formData?.brand}
            disabled={isUpdating}
          />
          <TextField
            margin="normal"
            required
            id="model"
            label="Model"
            name="model"
            type="string"
            fullWidth
            onChange={onChange}
            value={formData?.model}
            disabled={isUpdating}
          />
        </Box>

        <Box sx={{ display: "flex", gap: "0.5rem" }}>
          <TextField
            margin="normal"
            required
            fullWidth
            id="yearOfManufacture"
            label="Year of Manufacture"
            name="yearOfManufacture"
            onChange={onChange}
            value={formData?.yearOfManufacture}
            disabled={isUpdating}
            type="date"
            InputLabelProps={{
              shrink: true,
            }}
          />
          <TextField
            margin="normal"
            required
            fullWidth
            id="color"
            label="Color"
            name="color"
            onChange={onChange}
            value={formData?.color}
          />
        </Box>

        <Box sx={{ display: "flex", gap: "0.5rem" }}>
          <TextField
            margin="normal"
            required
            fullWidth
            id="mileage"
            label="Mileage"
            name="mileage"
            value={formData?.mileage}
            onChange={onChange}
          />
          <TextField
            margin="normal"
            required
            fullWidth
            id="fuelType"
            label="Fuel Type"
            name="fuelType"
            type="string"
            onChange={onChange}
            value={formData?.fuelType}
            disabled={isUpdating}
          />
        </Box>

        <Box sx={{ display: "flex", gap: "0.5rem" }}>
          <TextField
            margin="normal"
            required
            fullWidth
            id="dateOfPurchase"
            label="Date of Purchase"
            name="dateOfPurchase"
            onChange={onChange}
            value={formData?.dateOfPurchase}
            disabled={isUpdating}
            type="date"
            InputLabelProps={{
              shrink: true,
            }}
          />
          <FormControl fullWidth margin="normal" required>
            <InputLabel id="vehicleCondition-label">
              Vehicle Condition
            </InputLabel>
            <Select
              labelId="vehicleCondition-label"
              id="vehicleCondition"
              name="vehicleCondition"
              value={
                isUpdating
                  ? formData.vehicleCondition
                  : selectedVehicleCondition
              } // Ensure this is a single selected value
              onChange={onChange}
              label="Vehicle Condition"
              disabled={isUpdating}
            >
              {vehicleConditions.map((condition) => (
                <MenuItem key={condition} value={condition}>
                  {condition} {/* Display the condition value */}
                </MenuItem>
              ))}
            </Select>
          </FormControl>
        </Box>

        <Button
          type="submit"
          onClick={isUpdating ? updateVehicle : addVehicle}
          variant="contained"
          sx={{ mt: 3, mb: 1 }}
        >
         {isUpdating ? "Update Vehicle" : "Add Vehicle"}
        </Button>
      </Box>
    </Box>
  );
}
