package io.cucumber.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
}

