import { useState, useEffect } from "react";
import endpoints from "../../config/endpoints.json";
import useAxios from "../../hooks/axios";

export default function useVehicle() {
  const columns = [
    { field: "chassisNumber", headerName: "Chassis Number" },
    { field: "registrationNumber", headerName: "Registration Number" },
    { field: "brand", headerName: "Brand" },
    { field: "model", headerName: "Model" },
    { field: "yearOfManufacture", headerName: "Year of Manufacture" },
    { field: "color", headerName: "Color" },
    { field: "mileage", headerName: "Mileage" },
    { field: "fuelType", headerName: "Fuel Type" },
    { field: "dateOfPurchase", headerName: "Date of Purchase" },
    { field: "vehicleCondition", headerName: "Vehicle Condition" },
  ];

  const { customAxios } = useAxios();

  const [open, setOpen] = useState(false);
  const [openNotif, setOpenNotif] = useState(false);
  const [errors, setErrors] = useState({});
  const [isUpdating, setIsUpdating] = useState(false);
  const [updated, setUpdated] = useState(false);
  const [formData, setFormData] = useState({
    chassisNumber: "",
    registrationNumber: "",
    brand: "",
    model: "",
    yearOfManufacture: "",
    color: "",
    mileage: "",
    fuelType: "",
    dateOfPurchase: "",
    vehicleCondition: "", 
  });
  const [vehicles, setVehicles] = useState([]);
  const [identityNumber, setIdentityNumbers] = useState([]);
  const [vehicleConditions, setVehicleConditions] = useState([]);
  const [loading, setLoading] = useState(false);
  const [fetchError, setFetchError] = useState(null);
  const [selectedIdentityNumber, setSelectedIdentityNumber] = useState('');
const [selectedVehicleCondition, setSelectedVehicleCondition] = useState('');


  const handleOpen = () => setOpen(true);
  const handleClose = () => {
    setFormData({
      chassisNumber: "",
      registrationNumber: "",
      brand: "",
      model: "",
      yearOfManufacture: "",
      color: "",
      mileage: "",
      fuelType: "",
      dateOfPurchase: "",
      vehicleCondition: "",
      
    });
    setIsUpdating(false);
    setOpen(false);}
  const handleOpenNotif = () => setOpenNotif(true);
  const handleCloseNotif = () => setOpenNotif(false);

  const onChange = (e) => {
    const { name, value } = e.target;
    
    if (name === "ownerId") {
      setSelectedIdentityNumber(value); 
    }
  
    if (name === "vehicleCondition") {
      setSelectedVehicleCondition(value);
    }
  
   
    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  
    if (errors[name]) {
      setErrors((prevErrors) => ({
        ...prevErrors,
        [name]: undefined,
      }));
    }
  };

  const addVehicle = async () => {
    console.log(formData);
    try {
      const result = await customAxios({
        method: "POST",
        url: endpoints.addNewVehicle,
        data: formData,
      });
      console.log("Vehicle added successfully:", result.data);
      handleClose();
      handleOpenNotif();
      fetchVehicles();
    } catch (error) {
      console.error("Error adding vehicle:", error.response || error.message);
    }
  };
  const openUpdate = (row) => {
    console.log(row)
    handleOpen();
    setFormData(row);
    setIsUpdating(true);
  };
  const updateVehicle = async () => {
    try {
      const result = await customAxios({
        method: "PUT",
        url: endpoints.addNewVehicle,
        data: formData,
      });
      console.log("Vehicle Updated successfully:", result.data);
      handleClose();
      setUpdated(true);
      handleOpenNotif();
      fetchVehicles();
    } catch (error) {
      console.error("Error adding vehicles:", error.response || error.message);
    }
  };

  const fetchVehicles = async () => {
    setLoading(true);
    setFetchError(null);
    try {
      const response = await customAxios({
        method: "GET",
        url: endpoints.addNewVehicle,
      });
      setVehicles(response.data);
    } catch (error) {
      console.error(
        "Error fetching vehicles:",
        error.response || error.message
      );
      setFetchError(error.response?.data?.message || error.message);
    } finally {
      setLoading(false);
    }
  };
  const fetchIdentityNumbers = async () => {
    setLoading(true);
    setFetchError(null);
    try {
      const response = await customAxios({
        method: "GET",
        url: endpoints.identityNumber,
      });
      setIdentityNumbers(response.data);
    } catch (error) {
      console.error(
        "Error fetching vehicles:",
        error.response || error.message
      );
      setFetchError(error.response?.data?.message || error.message);
    } finally {
      setLoading(false);
    }
  };
console.log(vehicles)
  const fetchVehicleConditions = async () => {
    try {
      const response = await customAxios({
        method: "GET",
        url: endpoints.vehicleCondition,
      });
      setVehicleConditions(response.data);
    } catch (error) {
      console.error(
        "Error fetching vehicle conditions:",
        error.response || error.message
      );
    }
  };
 

  useEffect(() => {
    fetchVehicles();
    fetchVehicleConditions();
    fetchIdentityNumbers();
  }, []);

  return {
    onChange,
    handleOpen,
    handleClose,
    open,
    addVehicle,
    vehicles,
    vehicleConditions,
    identityNumber,
    loading,
    fetchError,
    columns,
    handleCloseNotif,
    handleOpenNotif,
    openNotif,
    selectedIdentityNumber,
    selectedVehicleCondition,
    formData,
    isUpdating,
    updateVehicle,
    updated,
    openUpdate
  };
}
