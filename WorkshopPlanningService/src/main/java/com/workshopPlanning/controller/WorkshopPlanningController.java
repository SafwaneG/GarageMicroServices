package com.workshopPlanning.controller;

import com.workshopPlanning.model.dto.WorkshopPlanningRequest;
import com.workshopPlanning.model.dto.WorkshopPlanningResponse;
import com.workshopPlanning.service.WorkshopPlanningService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
}
