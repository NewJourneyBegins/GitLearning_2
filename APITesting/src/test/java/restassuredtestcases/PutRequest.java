package restassuredtestcases;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class PutRequest {
	
	
	//you cannot update id here in put - remember
	@Test
	public void testPutCreatedUSer() {
		
		baseURI = "https://reqres.in/api";
		JSONObject reqData = new JSONObject();
		
		reqData.put("name","John");
		reqData.put("job","Analyst");

		System.out.println(reqData.toJSONString());

		given()
		//here i am sending this particular headers as request
			.header("Content-Type","application/json")
			.header("Connection","keep-alive")
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(reqData.toJSONString())
		.when()
			.put("/users/514")
		.then()
		//this is response we get
			.statusCode(200)
			.log().all();
//			.log().body();
	
	}

}
