import Modal from "../../components/modal";
import AddVehicle from "../../components/formVehicle";

export default function VehicleAdd({
  open,
  handleClose,
  onChange,
  addVehicle,
  vehicleConditions,
  identityNumber,
  selectedIdentityNumber,
  selectedVehicleCondition,
  formData,
  isUpdating,
  updateVehicle
  
}) {
  return (
    <Modal
      open={open}
      handleClose={handleClose}
      renderContent={
        <AddVehicle
          onChange={onChange}
          addVehicle={addVehicle}
          vehicleConditions={vehicleConditions}
          identityNumber={identityNumber}
          selectedIdentityNumber={selectedIdentityNumber}
          selectedVehicleCondition={selectedVehicleCondition}
          formData={formData}
          isUpdating={isUpdating}
          updateVehicle={updateVehicle}
        
        />
      }
      width="40%"
    />
  );
}
