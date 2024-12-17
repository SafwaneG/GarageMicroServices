import { Button, Box, TextField, Typography } from "@mui/material";

export default function AddNewClient({ onChange, addClient ,formData, isUpdating, updateClient }) {
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
        {isUpdating ? "Update Client" : "Add Client"}
      </Typography>
      <Box noValidate sx={{ mt: 1 }}>
        <TextField
          margin="normal"
          required
          fullWidth
          id="identityNumber"
          label="Identity Number"
          name="identityNumber"
          value={formData.identityNumber}
          onChange={onChange}
          disabled={isUpdating}
        />
        <Box sx={{ display: "flex", gap: "0.5rem" }}>
          <TextField
            margin="normal"
            required
            id="firstName"
            label="First Name"
            name="firstName"
            value={formData.firstName}
            fullWidth
            onChange={onChange}
          />
          <TextField
            margin="normal"
            required
            id="lastName"
            label="Last Name"
            name="lastName"
            value={formData.lastName}
            fullWidth
            onChange={onChange}
          />
        </Box>
        <Box sx={{ display: "flex", gap: "0.5rem" }}>
          <TextField
            margin="normal"
            required
            id="phoneNumber"
            label="Phone Number"
            name="phoneNumber"
            type="string"
            fullWidth
            value={formData.phoneNumber}
            onChange={onChange}
          />
          <TextField
            margin="normal"
            required
            id="email"
            label="Email"
            name="email"
            type="email"
            value={formData.email}
            fullWidth
            onChange={onChange}
          />
        </Box>
        <Typography component="h2" variant="h6" sx={{ mt: 3 }}>
          Address Details
        </Typography>
        <Box sx={{ display: "flex", gap: "0.5rem" }}>
          <TextField
            margin="normal"
            required
            fullWidth
            id="country"
            label="Country"
            name="address.country"
            value={formData.address.country}
            onChange={onChange}
          />
          <TextField
            margin="normal"
            required
            fullWidth
            id="city"
            label="City"
            name="address.city"
            value={formData.address.city}
            onChange={onChange}
          />
          <TextField
            margin="normal"
            required
            fullWidth
            id="street"
            label="Street"
            name="address.street"
            value={formData.address.street}
            onChange={onChange}
          />
          <TextField
            margin="normal"
            required
            fullWidth
            id="buildingNumber"
            label="Building Number"
            name="address.buildingNumber"
            value={formData.address.buildingNumber}
            type="number"
            onChange={onChange}
          />
        </Box>
        <Button
          type="submit"
          onClick={isUpdating ? updateClient : addClient}
          variant="contained"
          sx={{ mt: 3, mb: 1 }}
        >
          {isUpdating ? "Update Client" : "Add Client"}
        </Button>
      </Box>
    </Box>
  );
}
