package com.workshopPlanning.model.entity;

import com.mongodb.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "WorkshopPlanning")
public class WorkshopPlanning {
    @Id
    private String idWorkshop;
    @NonNull
    private Date planningDate;
    @NonNull
    private List<MaintenanceJob> maintenanceJobs;
    public  WorkshopPlanning(@NonNull Date planningDate ,@NonNull List<MaintenanceJob> jobs){
        this.planningDate=planningDate;
        this.maintenanceJobs=jobs;

    }
}

