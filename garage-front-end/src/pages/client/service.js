import { useState, useEffect } from "react";
import endpoints from "../../config/endpoints.json";
import useAxios from "../../hooks/axios";
export default function useClient() {
  const columns = [
    { field: "firstName", headerName: "First Name" },
    { field: "lastName", headerName: "Last Name" },
    { field: "phoneNumber", headerName: "Phone Number" },
    { field: "email", headerName: "Email" },
    {
      field: "address",
      headerName: "Address",
      nested: { fields: ["street", "city", "country", "buildingNumber"] },
    },
  ];
  const { customAxios } = useAxios();
  const [open, setOpen] = useState(false);
  const [openNotif, setOpenNotif] = useState(false);
  const [errors, setErrors] = useState({});
  const [isUpdating, setIsUpdating] = useState(false);
  const [updated, setUpdated] = useState(false);
  const [formData, setFormData] = useState({
    identityNumber: "",
    firstName: "",
    lastName: "",
    phoneNumber: "",
    email: "",
    address: {
      country: "",
      city: "",
      street: "",
      buildingNumber: "",
    },
  });
  const [clients, setClients] = useState([]);
  const [loading, setLoading] = useState(false);
  const [fetchError, setFetchError] = useState(null);

  const handleOpen = () => {
    setUpdated(false);
    setOpen(true);
  };
  const handleClose = () => {
    setFormData({
      identityNumber: "",
      firstName: "",
      lastName: "",
      phoneNumber: "",
      email: "",
      address: {
        country: "",
        city: "",
        street: "",
        buildingNumber: "",
      },
    });
    setIsUpdating(false);
    setOpen(false);
  };
  const handleOpenNotif = () => setOpenNotif(true);
  const handleCloseNotif = () => setOpenNotif(false);

  const onChange = (e) => {
    const { name, value } = e.target;

    if (name.includes("address.")) {
      const addressField = name.split(".")[1];
      setFormData((prevData) => ({
        ...prevData,
        address: {
          ...prevData.address,
          [addressField]: value,
        },
      }));
    } else {
      setFormData((prevData) => ({
        ...prevData,
        [name]: value,
      }));
    }

    if (errors[name]) {
      setErrors((prevErrors) => ({
        ...prevErrors,
        [name]: undefined,
      }));
    }
  };

  const addClient = async () => {
    try {
      const result = await customAxios({
        method: "POST",
        url: endpoints.addNewClient,
        data: formData,
      });
      console.log("Client added successfully:", result.data);
      handleClose();
      handleOpenNotif();
      fetchClients();
    } catch (error) {
      console.error("Error adding client:", error.response || error.message);
    }
  };
  const openUpdate = (row) => {
    handleOpen();
    setFormData(row);
    setIsUpdating(true);
  };

  const updateClient = async () => {
    try {
      const result = await customAxios({
        method: "PUT",
        url: endpoints.addNewClient,
        data: formData,
      });
      console.log("Client Updated successfully:", result.data);
      handleClose();
      setUpdated(true);
      handleOpenNotif();
      fetchClients();
    } catch (error) {
      console.error("Error adding client:", error.response || error.message);
    }
  };

  const fetchClients = async () => {
    setLoading(true);
    setFetchError(null);
    try {
      const response = await customAxios({
        method: "GET",
        url: endpoints.addNewClient,
      });
      setClients(response.data);
    } catch (error) {
      console.error("Error fetching clients:", error.response || error.message);
      setFetchError(error.response?.data?.message || error.message);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchClients();
  }, []);

  return {
    onChange,
    handleOpen,
    handleClose,
    open,
    addClient,
    clients,
    loading,
    fetchError,
    columns,
    handleCloseNotif,
    handleOpenNotif,
    openNotif,
    openUpdate,
    formData,
    isUpdating,
    updateClient,
    updated,
  };
}
