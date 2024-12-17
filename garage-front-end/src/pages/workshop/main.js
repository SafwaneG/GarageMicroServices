import Modal from "../../components/modal";
import AddWorkshop from "../../components/formWorkshop";

export default function WorkshopAdd({ open, handleClose, onChange, addWorkshop ,formData,addJob ,maintenanceStatus,registrationNumber}) {
  return (
    <Modal
      open={open}
      handleClose={handleClose}
      renderContent={
        <AddWorkshop
          onChange={onChange}
          addWorkshop={addWorkshop}
          formData={formData}
          addJob={addJob}
          maintenanceStatus={maintenanceStatus}
          registrationNumber={registrationNumber}
        />
      }
      width="50%"
     
    />
  );
}
