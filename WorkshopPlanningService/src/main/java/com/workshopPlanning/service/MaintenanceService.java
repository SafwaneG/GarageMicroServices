package com.workshopPlanning.service;

import com.workshopPlanning.model.VehicleCondition;
import com.workshopPlanning.model.entity.MaintenanceStatus;

public class MaintenanceService {

        public VehicleCondition mapStatusToCondition(MaintenanceStatus status) {
            switch (status) {
                case SCHEDULED:
                    return VehicleCondition.UNDERMAINTENANCE;
                case IN_PROGRESS:
                    return VehicleCondition.UNDERMAINTENANCE;
                case PAUSED:
                    return VehicleCondition.AWAITING;
                case COMPLETED:
                    return VehicleCondition.OPERATIONAL;
                case INCOMPLETED:
                    return VehicleCondition.INREPAIR;
                case CANCELLED:
                    return VehicleCondition.AWAITING;
                default:
                    throw new IllegalArgumentException("Unknown MaintenanceStatus: " + status);
            }
        }
    }


