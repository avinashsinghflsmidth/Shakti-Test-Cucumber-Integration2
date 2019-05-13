package io.cucumber.model;

import java.util.List;

import lombok.Data;

@Data
public class SignalResponseGet {
	private int page;
	private int pageSize;
	private boolean hasMore;
	private int totalPages;
	private int totalCount;
	private List<SignalResponse> values;
}
