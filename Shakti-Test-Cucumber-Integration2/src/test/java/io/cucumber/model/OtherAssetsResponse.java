package io.cucumber.model;

import lombok.Data;

@Data
public class OtherAssetsResponse {
	private String id;
	private String name;
	private String label;
	private String description;
	private String parentId;
}
