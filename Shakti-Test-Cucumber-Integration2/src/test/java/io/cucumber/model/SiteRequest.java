package io.cucumber.model;

import lombok.Data;

@Data
public class SiteRequest {
	private String country;
	private String crmNumber;
	private String description;
	private String latitude;
	private String longitude;
	private String name;
}
