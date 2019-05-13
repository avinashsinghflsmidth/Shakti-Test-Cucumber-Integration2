package io.cucumber.model;

import lombok.Data;

@Data
public class SignalRequest {
	private String name;
	private SignalType type;
	private String description;
	private boolean realTime;
	private String measurementType;
	private String parentId;
	private String sourceUnit;
	private String number;
	private String systemHigh;
	private String systemLow;
}
