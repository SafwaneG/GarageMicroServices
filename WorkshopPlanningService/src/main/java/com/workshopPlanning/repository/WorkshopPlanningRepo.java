package com.workshopPlanning.repository;

import com.workshopPlanning.model.entity.WorkshopPlanning;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface WorkshopPlanningRepo extends MongoRepository<WorkshopPlanning, String> {
   public WorkshopPlanning findByIdWorkshop(String id);

}
