package restassuredtestcases;

import static io.restassured.RestAssured.*;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class ExtractValuesFromResponses {
	
	@Test
	public void test() {
		baseURI = "https://reqres.in/api";
		
		JSONObject reqData = new JSONObject();
		
		reqData.put("email","eve.holt@reqres.in");
		reqData.put("password","pistol");

		String token =  given()	
							.body(reqData.toJSONString())
							.contentType(ContentType.JSON)
							.accept(ContentType.JSON)
							.header("charset","utf-8")
						.when()
							.post("/login")
						.then()
							.extract().path("token");
		System.out.println("TOKEN : " + token);

	}
	
	@Test
	public void test2() {
		//register user>>extract id and token
		//log in with above created user >>extract token
		//run get single user to find same user id>>validate name and job details
		//single <RESOURCE> use same usr id>>validate details
		//then update user details>>add validations>>search user and validate again
		//patch same user>>validate response>>search user>>validate
		//delet same user>>validate code>>search user>>validate
	}
}
