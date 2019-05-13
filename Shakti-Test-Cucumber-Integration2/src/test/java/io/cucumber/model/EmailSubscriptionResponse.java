package io.cucumber.model;

import lombok.Data;

@Data
public class EmailSubscriptionResponse {
	private String assetId;
	private String emailAddress;
	private String emailSubject;
	private String eventEmailSubscriptionId;
	private String id;
	private String includeEventsFromChildassets;
	private String minSeverity;
	private String occurence;
	private String sendAsDigest;
	private String createDate;
	private String assetName;
}

