package com.qa.testscripts;

import static io.restassured.RestAssured.when;

import java.util.List;
import java.util.Scanner;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.utilities.GeneralMethods;

import io.restassured.response.Response;

public class GetCityNameLanguageWithCode {
	GeneralMethods utility = new GeneralMethods();
	Scanner myObj = new Scanner(System.in);

	@Test(enabled=true)
	public void GetCityNameAndLangTest() {

		String pathParm = utility.readproperties("Code");
//		do {
		String uri = "https://restcountries.eu/rest/v2/callingcode/" + pathParm;
		Response response = when().
							get(uri).
							then().
							extract().
							response();

		Assert.assertEquals(response.getStatusCode(), 200);
		List<String> cityList = response.path("capital");
		List<List<String>> languageList = response.path("languages.name");
		
		System.out.println("City names of the code " + pathParm + " are below");
		for (String city : cityList) {
			System.out.println(city);
		}
		
		System.out.println("Languages of the code " + pathParm + " are below");
		for (List<String> languages : languageList) {
			
			for(String language:languages)
			System.out.println(language);
		}
		/*System.out.println("Enter Valid Code");
	    pathParm = myObj.nextLine();
		}while(!pathParm.equals(""));*/

	}
}
