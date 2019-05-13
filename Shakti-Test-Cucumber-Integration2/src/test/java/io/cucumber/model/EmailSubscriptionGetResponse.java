package io.cucumber.model;

import java.util.List;

import lombok.Data;

@Data
public class EmailSubscriptionGetResponse {
	private int page;
	private int pageSize;
	private boolean hasMore;
	private int totalPages;
	private int totalCount;
	private List<EmailSubscriptionResponse> subscriptions;
}

