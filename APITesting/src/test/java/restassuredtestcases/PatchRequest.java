package restassuredtestcases;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class PatchRequest {
	
	//checking push request of git in esclipse
	@Test
	public void testPatchCreatedUSer() {
		
		baseURI = "https://reqres.in/api";
		JSONObject reqData = new JSONObject();
		
		reqData.put("name","John");
		reqData.put("job","Carpenter");

		System.out.println(reqData.toJSONString());

		given()//not using header - yese hi			
			.body(reqData.toJSONString())
		.when()
			.patch("/users/514")
		.then()
		//this is response we get
			.statusCode(200)
			.log().body();
		//Just adding comment to check pull request in esclipse
	
	}
}
