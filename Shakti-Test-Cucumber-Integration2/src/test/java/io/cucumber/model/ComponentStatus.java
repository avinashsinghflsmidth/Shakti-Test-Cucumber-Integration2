package io.cucumber.model;

import lombok.Data;

@Data
public class ComponentStatus {
   private String assetHierarchy;
   private String timeSeries;
   private String fileUpload;
   private String fieldAgentGateway;
}
