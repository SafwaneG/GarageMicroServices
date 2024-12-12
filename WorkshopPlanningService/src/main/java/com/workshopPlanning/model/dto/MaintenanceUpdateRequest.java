package com.workshopPlanning.model.dto;

import com.workshopPlanning.model.entity.MaintenanceStatus;

import java.util.Date;

public class MaintenanceUpdateRequest {
    private MaintenanceStatus newStatus;
    private Date newEndDate;

    // Getters and setters
    public MaintenanceStatus getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(MaintenanceStatus newStatus) {
        this.newStatus = newStatus;
    }

    public Date getNewEndDate() {
        return newEndDate;
    }

    public void setNewEndDate(Date newEndDate) {
        this.newEndDate = newEndDate;
    }
}
