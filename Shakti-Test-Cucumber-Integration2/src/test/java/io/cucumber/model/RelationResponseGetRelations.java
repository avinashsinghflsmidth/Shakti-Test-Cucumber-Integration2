package io.cucumber.model;

import lombok.Data;

@Data
public class RelationResponseGetRelations {
	 private String id;
	 private String name;
	 private String description;
	 private String label;
	 private String parentId;
	 private String number;
	 private String type;
     private String subType;
}
