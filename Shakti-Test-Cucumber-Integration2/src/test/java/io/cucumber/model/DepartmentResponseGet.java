package io.cucumber.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DepartmentResponseGet {
	private int page;
	private int pageSize;
	private boolean hasMore;
	private int totalPages;
	private int totalCount;
	private List<DepartmentResponse> values;
}
