package com.hrm.data;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import commons.GlobalConstants;

public class Employee {
	public static Employee getJson(String jsonPath) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			return mapper.readValue(new File(GlobalConstants.PROJECT_PATH + jsonPath), Employee.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}



	@JsonProperty("firstname")
	String firstname;
	
	@JsonProperty("lastname")
	String lastname;
	
	@JsonProperty("fullname")
	String fullname;
	
	@JsonProperty("userPassword")
	String userPassword;
	
	@JsonProperty("statusValue")
	String statusValue;
	
	@JsonProperty("PersonalDetail")
	PersonalDetail personalDetail;
	
	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public String getFullname() {
		return fullname;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public String getStatusValue() {
		return statusValue;
	}

	
	public class PersonalDetail {
		@JsonProperty("editOtherId")
		String editOtherId;
		
		@JsonProperty("editLisenceExpireDate")
		String editLisenceExpireDate;
		
		@JsonProperty("editMaritalStatus")
		String editMaritalStatus;
		
		@JsonProperty("editNationality")
		String editNationality;
	}
	
	public String getEditOtherId() {
		return personalDetail.editOtherId;
	}
	
	public String getLisenceExpireDate() {
		return personalDetail.editLisenceExpireDate;
	}
	public String getMaritalStatus() {
		return personalDetail.editMaritalStatus;
	}
	public String getNationality() {
		return personalDetail.editNationality;
	}
	
}
