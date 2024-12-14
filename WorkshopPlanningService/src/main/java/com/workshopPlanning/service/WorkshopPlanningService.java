package com.workshopPlanning.service;
import com.workshopPlanning.event.WorkshopEvent;
import com.workshopPlanning.model.VehicleCondition;
import com.workshopPlanning.model.dto.*;
import com.workshopPlanning.model.entity.MaintenanceJob;
import com.workshopPlanning.model.entity.MaintenanceStatus;
import com.workshopPlanning.model.entity.WorkshopPlanning;
import com.workshopPlanning.repository.WorkshopPlanningRepo;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkshopPlanningService {
    private final WorkshopPlanningRepo workshopPlanningRepo;
    private final  VehicleService vehicleService;
    private final KafkaTemplate<String, WorkshopEvent> kafkaTemplate;


    public WorkshopPlanningResponse saveWorkshop(WorkshopPlanningRequest workshopPlanningRequest) {

        List<MaintenanceJob> maintenanceJobs = new ArrayList<>();
        List<String> registrationNumbers = new ArrayList<>();
        for (MaintenanceJobDto jobRequest : workshopPlanningRequest.jobs()) {
            registrationNumbers.add(jobRequest.registrationNumber());
        }
        VehicleResponseDtoList vehicles = vehicleService.getVehiclesByRegistrationNumbers(registrationNumbers);
        int index = 0;
        for (VehicleResponse vehicle : vehicles.vehicles()) {
            MaintenanceJobDto jobRequest = workshopPlanningRequest.jobs().get(index);

            MaintenanceJob maintenanceJob = new MaintenanceJob(
                    jobRequest.startTime(),
                    jobRequest.endTime(),
                    jobRequest.description(),
                    MaintenanceStatus.SCHEDULED,
                    vehicle.registrationNumber(),
                    vehicle.vehicleCondition()
            );
            maintenanceJobs.add(maintenanceJob);
            WorkshopEvent workshopPlacedEvent = new WorkshopEvent(vehicle.registrationNumber(), vehicle.email(), vehicle.name(),maintenanceJob.getStatus().toString(),0d);
            System.out.println("Sending event for vehicle: " + vehicle.registrationNumber());
            kafkaTemplate.send("workshop-plannified", workshopPlacedEvent);

            index++;
        }

        WorkshopPlanning workshopPlanning = new WorkshopPlanning(
                workshopPlanningRequest.planningDate(),
                maintenanceJobs
        );
        System.out.println("Saving workshop planning...");
        for (MaintenanceJobDto jobRequest : workshopPlanningRequest.jobs()) {
            System.out.println("Job Request Details: " + jobRequest);

        }
        WorkshopPlanning savedWorkshop = workshopPlanningRepo.save(workshopPlanning);

        System.out.println("Workshop planning saved."+savedWorkshop);
        return new WorkshopPlanningResponse(
                savedWorkshop.getIdWorkshop(),
                savedWorkshop.getPlanningDate(),
                savedWorkshop.getMaintenanceJobs()
        );
    }
    public void updateMaintenance(String workshopId, String jobId, MaintenanceUpdateRequest maintenanceUpdateRequest) {
        MaintenanceStatus newStatus = maintenanceUpdateRequest.getNewStatus();
        Date newEndDate = maintenanceUpdateRequest.getNewEndDate();
        Double newAmount = maintenanceUpdateRequest.getAmount();

        WorkshopPlanning workshopPlanning = workshopPlanningRepo.findByIdWorkshop(workshopId);
        if (workshopPlanning == null) {
            throw new IllegalArgumentException("Workshop planning not found for ID: " + workshopId);
        }

        MaintenanceJob jobToUpdate = workshopPlanning.getMaintenanceJobs().stream()
                .filter(job -> job != null && job.getJobId().equals(jobId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Maintenance job not found for ID: " + jobId));

        boolean statusChanged = newStatus != jobToUpdate.getStatus();

        if (newStatus != null) {
            MaintenanceService maintenanceService = new MaintenanceService();
            VehicleCondition vehicleCondition = maintenanceService.mapStatusToCondition(newStatus);

            jobToUpdate.setStatus(newStatus);
            jobToUpdate.setVehicleCondition(vehicleCondition);
        }

        if (newEndDate != null) {
            jobToUpdate.setEndTime(newEndDate);
        }

        workshopPlanningRepo.save(workshopPlanning);

        if (statusChanged) {
            VehicleResponse vehicleDetails = vehicleService.searchVehicle(jobToUpdate.getRegistrationNumber());

            if (vehicleDetails != null) {
                // Ensure amount is set (if newAmount is null, set it to 0)
                System.out.println(newAmount);
                double amountToSend = (newAmount != null && newStatus == MaintenanceStatus.COMPLETED) ? newAmount : 0;

                WorkshopEvent workshopEvent = new WorkshopEvent(
                        jobToUpdate.getRegistrationNumber(),
                        vehicleDetails.email(),
                        vehicleDetails.name(),
                        newStatus.toString(),
                        amountToSend // Ensure amount is always included
                );

                kafkaTemplate.send("workshop-plannified", workshopEvent);
                System.out.println("Client notified about status change to " + newStatus + " with amount: " + amountToSend);
            } else {
                System.err.println("Could not notify client: vehicle details not found.");
            }
        }
    }




    public List<WorkshopPlanningResponse> getAllWorkshopPlanning(){
            List<WorkshopPlanning> workshopPlannings = workshopPlanningRepo.findAll();
            return workshopPlannings.stream()
                    .map(workshopPlanning -> new WorkshopPlanningResponse(
                            workshopPlanning.getIdWorkshop(),
                            workshopPlanning.getPlanningDate(),
                            workshopPlanning.getMaintenanceJobs()
                    ))
                    .collect(Collectors.toList());
        }

    public WorkshopPlanningResponse getWorkshop(String id){
        WorkshopPlanning workshopPlanning = workshopPlanningRepo.findByIdWorkshop(id);
        return new WorkshopPlanningResponse(workshopPlanning.getIdWorkshop(),workshopPlanning.getPlanningDate(),workshopPlanning.getMaintenanceJobs());

    }


    }

