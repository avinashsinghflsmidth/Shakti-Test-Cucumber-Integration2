package io.cucumber.model;

import lombok.Data;

@Data
public class EvenServiceResponseEvents {
	private String id;
	private String assetId;
	private String assetName;
	private String assetType;
	private String timestamp;
	private String severity;
	private String occurance;
	private String description;
	private String attachmentUrl;
}
