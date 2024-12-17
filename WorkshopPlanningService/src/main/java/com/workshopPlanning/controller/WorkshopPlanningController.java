package com.workshopPlanning.controller;

import com.workshopPlanning.model.dto.MaintenanceUpdateRequest;
import com.workshopPlanning.model.dto.WorkshopPlanningRequest;
import com.workshopPlanning.model.dto.WorkshopPlanningResponse;
import com.workshopPlanning.model.entity.MaintenanceStatus;
import com.workshopPlanning.service.WorkshopPlanningService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/workshopPlanning")
@RequiredArgsConstructor
public class WorkshopPlanningController {
    private  final WorkshopPlanningService workshopPlanningService;
    @PostMapping
    public WorkshopPlanningResponse addWorkshop(@RequestBody WorkshopPlanningRequest workshopPlanningRequest){
            return  workshopPlanningService.saveWorkshop(workshopPlanningRequest);
    }
    @GetMapping
    public List<WorkshopPlanningResponse> getAllWorkshops(){
        return workshopPlanningService.getAllWorkshopPlanning();
    }
    @GetMapping("/{IdWorkshop}")
    public WorkshopPlanningResponse getWorkshopById(@PathVariable String IdWorkshop){
        return  workshopPlanningService.getWorkshop(IdWorkshop);
    }
    @PutMapping("/{idWorkshop}/{idMaintenance}")
    public void updateMaintenance(
                @PathVariable String idWorkshop,
                @PathVariable String idMaintenance,
                @RequestBody(required = false) MaintenanceUpdateRequest maintenanceUpdateRequest
    ) {
            workshopPlanningService.updateMaintenance(idWorkshop, idMaintenance,maintenanceUpdateRequest);
        }
    @GetMapping("/status")
    public List<String> getAllStatusWorkshop() {
        return Arrays.stream(MaintenanceStatus.values())
                .map(Enum::name)
                .toList();
    }
    }

