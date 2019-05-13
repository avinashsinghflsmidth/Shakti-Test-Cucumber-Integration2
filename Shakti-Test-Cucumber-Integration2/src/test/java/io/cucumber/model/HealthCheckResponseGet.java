package io.cucumber.model;

import lombok.Data;

@Data
public class HealthCheckResponseGet {
	private String status;
	private ComponentStatus componentsStatus;
}
