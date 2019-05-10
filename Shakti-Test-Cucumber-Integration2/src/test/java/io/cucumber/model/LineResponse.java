package io.cucumber.model;

import lombok.Data;

@Data
public class LineResponse {
	private String id;
	private String name;
	private String description;
	private String label;
	private String parentId;
}
