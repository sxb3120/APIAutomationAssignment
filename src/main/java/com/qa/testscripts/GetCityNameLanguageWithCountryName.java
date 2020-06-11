package com.qa.testscripts;

import static io.restassured.RestAssured.given;

import java.util.List;
import java.util.Scanner;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.utilities.GeneralMethods;

import io.restassured.response.Response;

public class GetCityNameLanguageWithCountryName {
	GeneralMethods utility = new GeneralMethods();
	Scanner myObj = new Scanner(System.in);

	@Test(enabled=true)
	public void GetCityNameAndLangFromCountryNameTest() {

		String pathParm = utility.readproperties("CountryName");
		String uri = "https://restcountries.eu/rest/v2/name/" + pathParm;
		uri=uri.replace("\"", "");
		Response response = given().param("fullText", "true").
							when().
							get(uri).
							then().
							extract().
							response();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		List<String> cityList = response.path("capital");
		List<List<String>> languageList = response.path("languages.name");
		
		System.out.println("City names of the country " + pathParm + " are below");
		for (String city : cityList) {
			System.out.println(city);
		}
		
		System.out.println("Languages of the country " + pathParm + " are below");
		for (List<String> languages : languageList) {
			
			for(String language:languages)
			System.out.println(language);
		}

	}
	
}
