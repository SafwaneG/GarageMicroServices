import Modal from "../../components/modal";
import AddClient from "../../components/form";

export default function ClientAdd({
  open,
  handleClose,
  onChange,
  addClient,
  formData,
  isUpdating,
  updateClient,
}) {
  return (
    <Modal
      open={open}
      handleClose={handleClose}
      renderContent={
        <AddClient
          onChange={onChange}
          addClient={addClient}
          formData={formData}
          isUpdating={isUpdating}
          updateClient={updateClient}
        />
      }
      width="40%"
    />
  );
}
