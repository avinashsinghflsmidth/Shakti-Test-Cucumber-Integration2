package io.cucumber.api;

import java.net.MalformedURLException;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.cucumber.model.SiteResponse;
import io.cucumber.model.SiteResponseGet;

public class SiteAPI {

	public SiteResponse postSite(String payload) {
		String postSiteUrlString = "/api/v1/sites";
		try {
			String ApiResponse = Api.postAPICallNew(postSiteUrlString, payload);
			if (!ApiResponse.isEmpty()) {
				ObjectMapper mapper = new ObjectMapper();
				SiteResponse response = mapper.readValue(ApiResponse, SiteResponse.class);
				return response;
			} else {
				System.out.println("response is empty ===>"+ApiResponse);
				return new SiteResponse();
			}

		}  catch (Exception e) {
			e.printStackTrace();
		}
		return new SiteResponse();
	}

	public SiteResponse getSite(String id) {
		String getSiteByIdUrlSt5ring = "/api/v1/sites/";
		try {
			String ApiResponse = Api.getAPICallNew(getSiteByIdUrlSt5ring + id);
			if (!ApiResponse.isEmpty()) {
				ObjectMapper mapper = new ObjectMapper();
				SiteResponse response = mapper.readValue(ApiResponse, SiteResponse.class);
				return response;
			} else {
				System.out.println("response is empty ===>"+ApiResponse);
				return new SiteResponse();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new SiteResponse();
	}

	public SiteResponseGet getAllSite() {
		String getAllSitesUrlString = "/api/v1/sites/";
		try {
			String ApiResponse = Api.getAPICallNew(getAllSitesUrlString);
			if (!ApiResponse.isEmpty()) {
				ObjectMapper mapper = new ObjectMapper();
				SiteResponseGet response = mapper.readValue(ApiResponse, SiteResponseGet.class);
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

	public SiteResponse putSite(String id, String payload) {
		String updateSiteUrlString = "/api/v1/sites/";
		try {
			String ApiResponse = Api.putAPICallNew(updateSiteUrlString + id, payload);
			if (!ApiResponse.isEmpty()) {
				ObjectMapper mapper = new ObjectMapper();
				SiteResponse response = mapper.readValue(ApiResponse, SiteResponse.class);
				return response;
			} else {
				System.out.println("response is empty ===>"+ApiResponse);
				return new SiteResponse();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public String deleteSite(String id) {
		String deleteSiteURlString = "/api/v1/sites/";
		try {
			String ApiResp = Api.deleteAPICallNew(deleteSiteURlString + id);
			return ApiResp;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
}
