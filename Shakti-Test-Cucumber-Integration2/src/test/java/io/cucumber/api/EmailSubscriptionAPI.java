package io.cucumber.api;

import java.net.MalformedURLException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.cucumber.model.EmailSubscriptionResponse;


public class EmailSubscriptionAPI {

	public EmailSubscriptionResponse postEmail(String payload) {
		String emailSubscriptionUrlString = "/api/v1/email-subscription";
		try {
			Api.loadProperties();
			String ApiResp = Api.postAPICallNew(emailSubscriptionUrlString, payload);
			if (!ApiResp.isEmpty()) {
				ObjectMapper mapper = new ObjectMapper();
				EmailSubscriptionResponse response = mapper.readValue(ApiResp, EmailSubscriptionResponse.class);
				return response;
			} else {
				System.out.println("response is empty");
				return new EmailSubscriptionResponse();
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();

		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return new EmailSubscriptionResponse();
	} 

	public List<EmailSubscriptionResponse> getAllEmail() {
		return null;}

	public String deleteEmail(String id) {
		return id;}		

}

