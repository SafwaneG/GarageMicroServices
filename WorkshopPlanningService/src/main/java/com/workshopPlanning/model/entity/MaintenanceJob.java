package com.workshopPlanning.model.entity;

import com.workshopPlanning.model.VehicleCondition;
import com.mongodb.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaintenanceJob {
    @Id
    private String jobId=new ObjectId().toString();;
    @NonNull
    private Date startTime;
    @NonNull
    private Date endTime;
    @NonNull
    private String description;
    @NonNull
    private MaintenanceStatus status;
    @NonNull
    private String registrationNumber;
    @NonNull
    private VehicleCondition vehicleCondition;
    public MaintenanceJob(@NonNull Date startTime,@NonNull Date endTime,@NonNull String description,@NonNull MaintenanceStatus status,@NonNull String registrationNumber,@NonNull VehicleCondition vehicleCondition){
        this.jobId = new ObjectId().toString();
        this.startTime=startTime;
        this.endTime=endTime;
        this.description=description;
        this.status=status;
        this.registrationNumber=registrationNumber;
        this.vehicleCondition=vehicleCondition;
    }
}


