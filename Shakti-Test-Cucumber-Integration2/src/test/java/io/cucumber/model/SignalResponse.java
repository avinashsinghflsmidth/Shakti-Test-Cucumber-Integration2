package io.cucumber.model;

import java.util.Map;

import lombok.Data;

@Data
public class SignalResponse {
	private String id;
	private String name;
	private String description;
	private String type;
	private String label;
	private boolean realTime;
	private String measurementType;
	private String sourceUnit;
	private String number;
	private String systemHigh;
	private String systemLow;
	private String parentId;
	private Map<String,String> metadata;
}
