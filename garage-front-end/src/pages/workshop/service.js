import { useState, useEffect } from "react";
import endpoints from "../../config/endpoints.json";
import useAxios from "../../hooks/axios";

export default function useWorkshop() {
  const { customAxios } = useAxios();
  const [open, setOpen] = useState(false);
  const [openStatus, setOpenStatus] = useState(false);
  const [openAdded, setOpenAdded] = useState(false);
  const [openNotif, setOpenNotif] = useState(false);

  const [errors, setErrors] = useState({});

  const [loading, setLoading] = useState(false);
  const [fetchError, setFetchError] = useState(null);
  const [selectedIds, setSelectedIds] = useState({
    jobId: null,
    workshopId: null,
  });
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);
  const handleOpenAdded = () => setOpenAdded(true);
  const handleCloseAdded = () => setOpenAdded(false);
  const handleOpenNotif = () => setOpenNotif(true);
  const handleCloseNotif = () => setOpenNotif(false);
  const handleOpenStatus = (jobId, workshopId) => {
    setSelectedIds({ jobId, workshopId });
    setOpenStatus(true);
  };

  const handleCloseStatus = () => setOpenStatus(false);
  const [workshop, setWorkshop] = useState([]);
  const [maintenanceUpdateRequest, setMaintenanceUpdateRequest] = useState({
    newStatus: "",
    newEndDate: "",
  });
  const [maintenanceStatus, setMaintenanceStatus] = useState([]);
  const [selectedMaintenanceStatus, setSelectedMaintenanceStatus] =
    useState("");
  const [registrationNumber, setRegistrationNumber] = useState([]);
  const [selectedRegistrationNumber, setSelectedRegistrationNumber] =
    useState("");
  const [formData, setFormData] = useState({
    planningDate: "",
    jobs: [
      {
        startTime: "",
        endTime: "",
        description: "",
        status: "",
        registrationNumber: "",
        amount: "",
      },
    ],
  });

  const onChange = (event) => {
    const { name, value } = event.target;

    if (name.includes("jobs")) {
      const [_, index, field] = name.split(".");
      setFormData((prevData) => {
        const updatedJobs = [...prevData.jobs];
        updatedJobs[+index] = {
          ...updatedJobs[+index],
          [field]: value,
        };
        return { ...prevData, jobs: updatedJobs };
      });
    } else {
      setFormData({ ...formData, [name]: value });
    }
  };

  const addJob = () => {
    setFormData((prevData) => ({
      ...prevData,
      jobs: [
        ...prevData.jobs,
        {
          startTime: "",
          endTime: "",
          description: "",
          status: "",
          registrationNumber: "",
        },
      ],
    }));
  };
  const fetchMaintenanceStatus = async () => {
    try {
      const response = await customAxios({
        method: "GET",
        url: endpoints.maintenanceStatus,
      });
      setMaintenanceStatus(response.data);
      console.log(maintenanceStatus);
    } catch (error) {
      console.error(
        "Error fetching maintenance status:",
        error.response || error.message
      );
    }
  };
  const fetchRegistrationsNumbers = async () => {
    try {
      const response = await customAxios({
        method: "GET",
        url: endpoints.registrationsNumbers,
      });
      setRegistrationNumber(response.data);
    } catch (error) {
      console.error("Error fetching  :", error.response || error.message);
    }
  };
  const addWorkshop = async () => {
    console.log(formData);
    try {
      const response = await customAxios({
        method: "POST",
        url: endpoints.createWorkshop,
        data: formData,
      });

      setFormData((prevData) => ({
        planningDate: "",
        jobs: [
          {
            startTime: "",
            endTime: "",
            description: "",
            status: "",
            registrationNumber: "",
          },
        ],
      }));
      handleClose();
      handleOpenAdded();
      fetchWorkshop();
    } catch (error) {
      console.error(
        "Error fetching maintenance status:",
        error.response || error.message
      );
    }
  };
  const fetchWorkshop = async () => {
    setLoading(true);
    setFetchError(null);
    try {
      const response = await customAxios({
        method: "GET",
        url: endpoints.createWorkshop,
      });
      setWorkshop(response.data);
    } catch (error) {
      console.error("Error fetching :", error.response || error.message);
      setFetchError(error.response?.data?.message || error.message);
    } finally {
      setLoading(false);
    }
  };
  const updateMaintenance = async (newStatus, newEndDate, amount) => {
    console.log(newStatus, newEndDate, amount);
    try {
      if (!selectedIds.jobId || !selectedIds.workshopId) {
        throw new Error("No job or workshop selected");
      }

      const data = {
        newStatus,
        newEndDate,
      };

      if (newStatus === "COMPLETED") {
        data.amount = amount;
      }
      console.log("hello data");
      console.log(data);
      const response = await customAxios({
        method: "PUT",
        url: `${endpoints.createWorkshop}/${selectedIds.workshopId}/${selectedIds.jobId}`,
        data,
      });

      console.log("Job updated successfully");
      handleCloseStatus();
      fetchWorkshop();
      handleOpenNotif();

      if (response.status === 200) {
        console.log("Workshop updated");
      }
    } catch (error) {
      console.error("Error updating maintenance:", error.message);
    }
  };

  useEffect(() => {
    fetchMaintenanceStatus();
    fetchRegistrationsNumbers();
    fetchWorkshop();
  }, []);

  return {
    onChange,
    handleOpen,
    handleClose,
    open,
    loading,
    fetchError,
    handleCloseNotif,
    handleOpenNotif,
    openNotif,
    formData,
    addJob,
    addWorkshop,
    selectedMaintenanceStatus,
    maintenanceStatus,
    selectedRegistrationNumber,
    registrationNumber,
    workshop,
    openStatus,
    handleCloseStatus,
    handleOpenStatus,
    updateMaintenance,
    maintenanceUpdateRequest,
    setMaintenanceUpdateRequest,
    handleCloseAdded,
    openAdded,
    
  };
}
