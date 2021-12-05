package com.hrm.data;

import com.github.javafaker.Faker;

public class DataHelper {
	private Faker faker = new Faker();
	
	public static DataHelper getData() {
		return new DataHelper();
	}
	
	public String getFirstName() {
		return faker.address().firstName();
	}
	public String getLastName() {
		return faker.address().lastName();
	}
}
