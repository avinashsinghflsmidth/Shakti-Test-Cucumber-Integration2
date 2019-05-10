package io.cucumber.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class DepartmentResponse {
	private String id;
	private String label;
	private String name;
	private String number;
	private String description;
	private String parentId;
}
