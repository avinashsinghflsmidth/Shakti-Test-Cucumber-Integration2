package io.cucumber.model;

public class SignalSubscriptionPostResponse {
	private String id;
	private String callbackUrl;
	private String parentId;
	private String owner;

	public SignalSubscriptionPostResponse() {
	}

	public SignalSubscriptionPostResponse(String id, String callbackUrl, String parentId, String owner) {
		super();
		this.id = id;		
		this.callbackUrl = callbackUrl;
		this.parentId = parentId;
		this.owner = owner;
		
	}

	public String getCallbackUrl() {
		return callbackUrl;
	}

	public String getParentId() {
		return parentId;
	}
	
	public String getId(){
		return id;
	}
	
	public String getOwner(){
		return owner;
	}
	
	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
	public void setId(String id){
		this.id = id;
	}
	
	public void setOwner(String owner){
		this.owner = owner;
	}

	@Override
	public String toString() {
		return "SignalSubscriptionRequest [id=" + id + ", callbackUrl=" + callbackUrl + ", parentId=" + parentId + ", owner="
				+ owner + "]";
	}

	

}
