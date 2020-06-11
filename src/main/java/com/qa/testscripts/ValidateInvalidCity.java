package com.qa.testscripts;

import static io.restassured.RestAssured.when;

import java.util.Scanner;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.utilities.GeneralMethods;

import io.restassured.response.Response;

public class ValidateInvalidCity {
	GeneralMethods utility = new GeneralMethods();
	Scanner myObj = new Scanner(System.in);

	
	@Test
	public void validateResponseCodeInvalidCityTest() {
		String pathParm = utility.readproperties("InvalidCity");
		do {
		String uri = "https://restcountries.eu/rest/v2/capital/" + pathParm;
		Response response = when().
							get(uri).
							then().
							extract().
							response();
		int responseCode=response.getStatusCode();
		Assert.assertEquals(responseCode, 404,"response code is "+responseCode+ " as expected for invalid city");
		System.out.println("response code is "+responseCode+ " as expected for invalid city");
		
	    System.out.println("Enter invalid city name");
	    pathParm = myObj.nextLine();
	    
	}while(!pathParm.equals(""));
	}
	
}
