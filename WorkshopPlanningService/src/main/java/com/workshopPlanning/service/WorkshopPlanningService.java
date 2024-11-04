package com.workshopPlanning.service;

import com.workshopPlanning.event.WorkshopPlacedEvent;
import com.workshopPlanning.model.dto.VehicleResponse;
import com.workshopPlanning.model.dto.WorkshopPlanningRequest;
import com.workshopPlanning.model.dto.WorkshopPlanningResponse;
import com.workshopPlanning.model.entity.MaintenanceJob;
import com.workshopPlanning.model.entity.WorkshopPlanning;
import com.workshopPlanning.model.dto.MaintenanceJobDto;
import com.workshopPlanning.repository.WorkshopPlanningRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkshopPlanningService {
    private final WorkshopPlanningRepo workshopPlanningRepo;
    private final  VehicleService vehicleService;
    private final KafkaTemplate<String, WorkshopPlacedEvent> kafkaTemplate;


    public WorkshopPlanningResponse saveWorkshop(WorkshopPlanningRequest workshopPlanningRequest){
        VehicleResponse vehicle = null;
        List<MaintenanceJob> maintenanceJobs = new ArrayList<>();
        for (MaintenanceJobDto jobRequest : workshopPlanningRequest.jobs()) {
            vehicle= vehicleService.searchVehicle(jobRequest.registrationNumber());

            if (vehicle == null) {
                throw new IllegalArgumentException("Vehicle not found for registration number: " + jobRequest.registrationNumber());
            }

            MaintenanceJob maintenanceJob = new MaintenanceJob(
                    jobRequest.startTime(),
                    jobRequest.endTime(),
                    jobRequest.description(),
                    jobRequest.status(),
                   vehicle.vehicleCondition()
            );
            maintenanceJobs.add(maintenanceJob);
        }


        WorkshopPlanning workshopPlanning = new WorkshopPlanning(
                workshopPlanningRequest.planningDate(),
                maintenanceJobs
        );
        System.out.println("im here");
        WorkshopPlanning savedWorkshop = workshopPlanningRepo.save(workshopPlanning);
        WorkshopPlacedEvent workshopPlacedEvent= new WorkshopPlacedEvent(vehicle.registrationNumber(),vehicle.email(),vehicle.name());
        System.out.println("start sending");
        kafkaTemplate.send("workshop-plannified",workshopPlacedEvent);
        System.out.println("finished sendign");
        return new WorkshopPlanningResponse(savedWorkshop.getIdWorkshop(),savedWorkshop.getPlanningDate(), savedWorkshop.getMaintenanceJobs());
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

