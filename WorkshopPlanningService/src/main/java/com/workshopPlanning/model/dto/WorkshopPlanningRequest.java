package com.workshopPlanning.model.dto;

import java.util.Date;
import java.util.List;

public record WorkshopPlanningRequest (Date planningDate,List<MaintenanceJobDto> jobs){};
