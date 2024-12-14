package com.workshopPlanning.model.dto;

import com.workshopPlanning.model.entity.MaintenanceStatus;

import java.util.Date;

public record MaintenanceJobDto (Date startTime, Date endTime, String description, String registrationNumber){}
