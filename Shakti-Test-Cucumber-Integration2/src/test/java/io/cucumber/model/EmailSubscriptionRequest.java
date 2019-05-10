package io.cucumber.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmailSubscriptionRequest{

	private String assetId;
	private String emailAddress;
	private String emailSubject;
	private String includeEventsFromChildassets;
	private String minSeverity;
	private String occurence;
	private String sendAsDigest;
	
}

