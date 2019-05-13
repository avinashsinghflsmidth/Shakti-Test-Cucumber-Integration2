package io.cucumber.model;

import lombok.Data;

@Data
public class SiteResponse {
	private String id;
	private String country;
	private String crmNumber;
	private String description;
	private String label;
	private String latitude;
	private String longitude;
	private String name;
}
