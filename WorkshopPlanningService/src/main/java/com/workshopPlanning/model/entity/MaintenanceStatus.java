package com.workshopPlanning.model.entity;

    public enum MaintenanceStatus {
        SCHEDULED("Maintenance is scheduled for a specific date and time but has not yet started."),
        IN_PROGRESS("Maintenance has started and is currently underway."),
        PAUSED("Maintenance is temporarily halted, for example, due to awaiting parts."),
        COMPLETED("Maintenance is finished, and the vehicle is ready to be picked up or proceed to billing."),
        INCOMPLETED("in his mainetnace wich completed but the vehicle is not opertaional because we can repaire this vehicel"),
        CANCELLED("Maintenance has been cancelled for a specific reason.");

        private final String description;

        MaintenanceStatus(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }


