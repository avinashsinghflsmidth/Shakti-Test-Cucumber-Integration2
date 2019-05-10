package io.cucumber.model;

import lombok.Data;

@Data
public class EquipmentResponse {
	private String id;
	private String name;
	private String description;
	private String type;
	private String subType;
	private String number;
	private String label;
	private String parentId;
}
