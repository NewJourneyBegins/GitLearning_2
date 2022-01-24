package restassuredtestcases;

import static org.hamcrest.Matchers.equalTo;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class PostRequest {

	@Test
	public void testPostCreateUSer() {
		
		
		baseURI = "https://reqres.in/api";
		JSONObject reqData = new JSONObject();
		reqData.put("name","John");
		reqData.put("job","Teacher");

		System.out.println(reqData.toJSONString());

		given()
		//here i am sending this particular headers as request
			.header("Content-Type","application/json")
			.header("Connection","keep-alive")
			// line 28 and 32 meaning is same.
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(reqData.toJSONString())
		.when()
			.post("/users")
		.then()
		//this is response we get
			.statusCode(201)
			.log().all();
//			.log().body();
	}
	
	@Test
	public void testPostRegisterSuccessfully() {
		
		
		baseURI = "https://reqres.in/api";
		JSONObject reqData = new JSONObject();
		reqData.put("email","eve.holt@reqres.in");
		reqData.put("password","pistol");

		System.out.println(reqData.toJSONString());

		given()
			.header("Content-Type","application/json")
			.header("Connection","keep-alive")
			.accept(ContentType.JSON)
			.body(reqData.toJSONString())
		.when()
			.post("/register")
		.then()
			.statusCode(200)
			.log().all();

//		response we should get
//		{
//		    "id": 4,
//		    "token": "QpwL5tke4Pnpja7X4"
//		}
	}
	
	@Test
	public void testPostRegisterUnSuccessfully() {
		
		
		baseURI = "https://reqres.in/api";
		JSONObject reqData = new JSONObject();
		reqData.put("email","sydney@fife");

		System.out.println(reqData.toJSONString());

		given()
			.header("Connection","keep-alive")
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(reqData.toJSONString())
		.when()
			.post("/register")
		.then()
		//verify status code 
			.statusCode(400)
		//validation for body 
			.body("error", equalTo("Missing password") )
			.log().all();
		
//		response we should get
//		{
//		    "error": "Missing password"
//		}
		
	}
	
	@Test
	public void testPostLoginSuccessfully() {
		
		
		baseURI = "https://reqres.in/api";
		JSONObject reqData = new JSONObject();
		reqData.put("email","eve.holt@reqres.in");
		reqData.put("password","pistol");

		System.out.println(reqData.toJSONString());

		given()
			.header("Content-Type","application/json")
			.header("Connection","keep-alive")
			.accept(ContentType.JSON)
			.body(reqData.toJSONString())
		.when()
			.post("/login")
		.then()
		//verify status code 
			.statusCode(200)
			.log().all();
	}
	
	@Test
	public void testPostLoginUnSuccessfully() {
		
		
		baseURI = "https://reqres.in/api";
		JSONObject reqData = new JSONObject();
		reqData.put("email","peter@klaven");
	
		System.out.println(reqData.toJSONString());

		given()
		    .header("Content-Type","application/json")
		    .header("Connection","keep-alive")
		    .accept(ContentType.JSON)
			.body(reqData.toJSONString())
		.when()
			.post("/login")
		.then()
			.statusCode(400)
			//validation for body 
			.body("error", equalTo("Missing password") )
			.log().body()
			//if validation fails , status is displayed
			.log().ifValidationFails(LogDetail.STATUS);
	}

}
