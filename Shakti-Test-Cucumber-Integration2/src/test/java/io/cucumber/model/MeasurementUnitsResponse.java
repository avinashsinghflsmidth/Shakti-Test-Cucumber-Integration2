package io.cucumber.model;

import java.util.List;

import lombok.Data;

@Data
public class MeasurementUnitsResponse {
	private String measurementUnit;
	private String measurementType;
	private List<String> aliases;
}
