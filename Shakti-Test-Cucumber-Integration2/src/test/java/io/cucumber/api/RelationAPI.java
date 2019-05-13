package io.cucumber.api;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.cucumber.model.RelationResponseGet;

/*
 * class RelationAPI having Api url and method details..
 */
public class RelationAPI {

	/*
	 * method getAllRelations calling GET api..
	 */
	public RelationResponseGet getAllRelations(String strParentId) {
		final String getAllRelationUrlString = "/api/v1/relations?parentId="+strParentId;
		try {
			String ApiResponse = Api.getAPICallNew(getAllRelationUrlString);
			if (!ApiResponse.isEmpty()) {
				ObjectMapper mapper = new ObjectMapper();
				RelationResponseGet response = mapper.readValue(ApiResponse, RelationResponseGet.class);
				return response;
			} else {
				System.out.println("response is empty ===>"+ApiResponse);
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
