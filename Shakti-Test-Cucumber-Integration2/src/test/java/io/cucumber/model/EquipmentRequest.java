package io.cucumber.model;

import lombok.Data;

@Data
public class EquipmentRequest {

	private String name;
	private String description;
	private String type;
	private String subType;
	private String number;
	private String parentId;

}
