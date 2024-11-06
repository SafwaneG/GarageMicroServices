package com.workshopPlanning.model;

public enum MaintenanceStatus {
    SCHEDULED("Your vehicle maintenance is scheduled for a specific date and time, but has not yet started. We will notify you once the work begins."),
    IN_PROGRESS("Your vehicle maintenance is currently in progress. Our team is working on it, and we will keep you updated on the status."),
    PAUSED("Your vehicle's maintenance has been temporarily paused due to awaiting parts or other reasons. We will notify you once the work resumes."),
    COMPLETED("Your vehicle's maintenance has been completed. It is now ready for pickup, or we can proceed with billing. Please let us know how you'd like to proceed."),
    INCOMPLETED("Although the maintenance is completed, your vehicle is still not operational as additional repairs are required. We will contact you with more details."),
    CANCELLED("The scheduled maintenance for your vehicle has been cancelled due to specific reasons. Please contact us to reschedule or discuss the next steps.");

    private final String description;

    MaintenanceStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}



