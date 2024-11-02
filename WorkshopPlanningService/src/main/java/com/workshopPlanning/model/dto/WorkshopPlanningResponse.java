package com.workshopPlanning.model.dto;

import com.workshopPlanning.model.entity.MaintenanceJob;

import java.util.Date;
import java.util.List;

public record WorkshopPlanningResponse (String id,Date planningDate, List<MaintenanceJob> jobs){};
