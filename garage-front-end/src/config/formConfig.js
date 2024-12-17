export const config = {
  client: [
    {
      id: "identityNumber",
      label: "Identity Number",
      type: "text",
      required: true,
    },
    { id: "firstName", label: "First Name", type: "text", required: true },
    { id: "lastName", label: "Last Name", type: "text", required: true },
    { id: "phoneNumber", label: "Phone Number", type: "text", required: true },
    { id: "email", label: "Email", type: "email", required: true },
    {
      id: "address",
      label: "Address",
      type: "group",
      fields: [
        { id: "country", label: "Country", type: "text", required: true },
        { id: "city", label: "City", type: "text", required: true },
        { id: "street", label: "Street", type: "text", required: true },
        {
          id: "buildingNumber",
          label: "Building Number",
          type: "number",
          required: true,
        },
      ],
    },
  ],
  vehicle: [
    {
      id: "ownerIdentityNumber",
      label: "Owner Identity Number",
      type: "text",
      required: true,
    },
    {
      id: "chassisNumber",
      label: "Chassis Number",
      type: "text",
      required: true,
    },
    {
      id: "registrationNumber",
      label: "Registration Number",
      type: "text",
      required: true,
    },
    { id: "brand", label: "Brand", type: "text", required: true },
    { id: "model", label: "Model", type: "text", required: true },
    {
      id: "yearOfManufacture",
      label: "Year of Manufacture",
      type: "date",
      required: true,
    },
    { id: "color", label: "Color", type: "text", required: true },
    { id: "mileage", label: "Mileage", type: "number", required: true },
    { id: "fuelType", label: "Fuel Type", type: "text", required: true },
    {
      id: "dateOfPurchase",
      label: "Date of Purchase",
      type: "date",
      required: true,
    },
    {
      id: "vehicleCondition",
      label: "Vehicle Condition",
      type: "text",
      required: true,
    },
  ],
  workshop: [
    {
      id: "planningDate",
      label: "Planning Date",
      type: "datetime-local",
      required: true,
    },
    {
      id: "jobs",
      label: "Jobs",
      type: "array",
      itemSchema: [
        {
          id: "startTime",
          label: "Start Time",
          type: "datetime-local",
          required: true,
        },
        {
          id: "endTime",
          label: "End Time",
          type: "datetime-local",
          required: true,
        },
        {
          id: "description",
          label: "Description",
          type: "text",
          required: true,
        },
        { id: "status", label: "Status", type: "text", required: true },
        {
          id: "registrationNumber",
          label: "Registration Number",
          type: "text",
          required: true,
        },
      ],
    },
  ],
};
