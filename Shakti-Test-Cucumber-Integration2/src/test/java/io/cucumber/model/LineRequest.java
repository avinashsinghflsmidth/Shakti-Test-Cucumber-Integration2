package io.cucumber.model;

import lombok.Data;

@Data
public class LineRequest {
	private String name;
	private String description;
	private String parentId;
}
