package io.cucumber.skeleton;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.api.SignalAPI;
import io.cucumber.datatable.DataTable;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.model.SignalResponse;
import io.cucumber.model.SignalResponseGet;

/*
 * class Signal for testing Signal feature BDD..
 */
public class Signal {
	List<Map<String, String>> list;
	HashMap<String, String> dataTableHashMap;
	SignalResponse getAfterPostResp;
	SignalResponseGet getAllAfterPostResp;
	SignalResponse putResp;
	SignalResponse getAfterPutResp;
	SignalResponse getAfterDeleteResp;
	SignalResponseGet getAllAfterDeleteResp;
	SignalResponse postResp;
	SignalResponse postBatchResp;

	private int totalRecordAfterPost;
	private int totalRecordAfterDelete;

	/*
	 * This method givenData() used for getting the given data from data-table in the feature..
	 */
	@Given("^I have below Details for Signal$")
	public void givenData(DataTable dataTable) {
		list = dataTable.asMaps(String.class, String.class);
		for(Map<String,String> map : list) {
			dataTableHashMap = new HashMap<String, String>(); 
			dataTableHashMap.putAll(map); 
		}
		System.out.println("dataTableHashMap===>"+dataTableHashMap);
	}

	/*
	 * This method postData() is used for POST, GET, PUT, GET, DELETE, GET operations..
	 */
	@When("I post get getAll put get delete get getall Signal")
	public void postSignals() {
		SignalAPI signalApi = new SignalAPI();
		ObjectMapper mapperObj = new ObjectMapper();
		String signalData = "";
		long time = System.currentTimeMillis();
		try {
			dataTableHashMap.put("number", dataTableHashMap.get("number")+time);
			signalData = mapperObj.writeValueAsString(dataTableHashMap);
			System.out.println("signalData :::"+signalData);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		postResp = signalApi.postSignal(signalData);
		System.out.println("The Signal ID is :" + postResp.getId());
		getAfterPostResp = signalApi.getSignal(postResp.getId());

		getAllAfterPostResp = signalApi.getAllSignal();
		totalRecordAfterPost = getAllAfterPostResp.getTotalCount();
		
		System.out.println("totalRecordAfterPost count ===>"+totalRecordAfterPost);


//		dataTableHashMap.put("description", dataTableHashMap.get("description")+"New");
//		dataTableHashMap.put("number", dataTableHashMap.get("number")+time);
//		String newSignalData = "";
//		try {
//			newSignalData = mapperObj.writeValueAsString(dataTableHashMap);
//		} catch (JsonProcessingException e) {
//			e.printStackTrace();
//		}

		
		String newSignalData = "{\"description\":\"" + list.get(0).get("description") + "New" + "\",";
		newSignalData = newSignalData + "\"measurementType\":\"" + list.get(0).get("measurementType") + "\",";
		newSignalData = newSignalData + "\"name\":\"" + list.get(0).get("name") + "\",";
		newSignalData = newSignalData + "\"number\":\"" + list.get(0).get("number") +time+ "\",";
		newSignalData = newSignalData + "\"parentId\":\"" + list.get(0).get("parentId") + "\",";
		newSignalData = newSignalData + "\"realTime\":\"" + list.get(0).get("realTime") + "\",";
		newSignalData = newSignalData + "\"signalId\":\"" + list.get(0).get("signalId") + "\",";
		newSignalData = newSignalData + "\"sourceUnit\":\"" + list.get(0).get("sourceUnit") + "\",";
		newSignalData = newSignalData + "\"systemHigh\":\"" + list.get(0).get("systemHigh") + "\",";
		newSignalData = newSignalData + "\"systemLow\":\"" + list.get(0).get("systemLow") + "\",";
		newSignalData = newSignalData + "\"type\":\"" + list.get(0).get("type") + "\"}";
		System.out.println("updating the data :"+newSignalData);
		putResp = signalApi.putSignal(postResp.getId(), newSignalData);

		getAfterPutResp = signalApi.getSignal(postResp.getId());

		signalApi.deleteSignal(postResp.getId());

		getAfterDeleteResp = signalApi.getSignal(postResp.getId());

		getAllAfterDeleteResp = signalApi.getAllSignal();

		totalRecordAfterDelete = getAllAfterDeleteResp.getTotalCount();
		
		System.out.println("totalRecordAfterDelete count ===>"+totalRecordAfterDelete);

	}

	/*
	 * This method validateResults() is used for validating the responses..
	 */
	@Then("validate the Signal results")
	public void validateResults() {
		if (postResp != null && postResp.getId() != null && !postResp.getId().isEmpty()) {
			if (getAfterPostResp != null && getAfterPostResp.getId() != null && !getAfterPostResp.getId().isEmpty()) {
				if (getAfterPostResp.getId().equals(postResp.getId())) {
					if (getAllAfterPostResp != null && getAllAfterPostResp.getValues().size() > 0) {
						SignalResponse signalResponse = getAllAfterPostResp.getValues().stream().filter(s -> s.getId().equals(postResp.getId())).findAny().orElse(null);
						if(signalResponse != null) {
							System.out.println("id present in first page!");
						}else {
							// What if not found in first page, Do we have to retrieve all pages???
							// throw new AssertionError("Id not present in get all response");
						}
					} else {
						throw new AssertionError("Id not present in  get all response");
					}
				} else {
					throw new AssertionError("Id in get and post responses are not matching");
				}
			} else {
				throw new AssertionError("Id was not found in get response");
			}
		} else {
			throw new AssertionError("Id was not found in posted response");
		}

		// Check signal details after PUT
		if (getAfterPutResp != null && getAfterPutResp.getId() != null && !getAfterPutResp.getId().isEmpty()) {
			if (!getAfterPutResp.getDescription().equals(postResp.getDescription())) {
				System.out.println("signal updated!!");
			} else {
				throw new AssertionError("Othersignals details not updated");
			}
		}

		// Check signal details after DELETE
		SignalResponse signalResponse = getAllAfterDeleteResp.getValues().stream().filter(s -> s.getId().equals(postResp.getId())).findAny().orElse(null);
		if(signalResponse != null) {
			throw new AssertionError("Id is present in  get all response after delete");
		}else {
			System.out.println("Signals deleted!!");
		}
		
		Assert.assertEquals(totalRecordAfterDelete, totalRecordAfterPost-1);
	}
}
