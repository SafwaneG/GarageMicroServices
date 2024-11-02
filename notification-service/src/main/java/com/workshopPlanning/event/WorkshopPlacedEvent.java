package com.workshopPlanning.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkshopPlacedEvent {
    private String registrationNumber;
    private String email;
}
