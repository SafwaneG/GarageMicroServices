package com.workshopPlanning.model.entity;

import com.workshopPlanning.model.VehicleCondition;
import com.mongodb.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "MaintenanceJob")
public class MaintenanceJob {
    @Id
    private Long jobId;
    @NonNull
    private Date startTime;
    @NonNull
    private Date endTime;
    @NonNull
    private String description;
    @NonNull
    private MaintenanceStatus status;
    @NonNull
    private VehicleCondition vehicleCondition;
    public MaintenanceJob(@NonNull Date startTime,@NonNull Date endTime,@NonNull String description,@NonNull MaintenanceStatus status,@NonNull VehicleCondition vehicleCondition){
        this.startTime=startTime;
        this.endTime=endTime;
        this.description=description;
        this.status=status;
        this.vehicleCondition=vehicleCondition;
    }
}


