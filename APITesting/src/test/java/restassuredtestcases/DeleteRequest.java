package restassuredtestcases;

import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

public class DeleteRequest {
	
	@Test
	public void testDeleteCreatedUser() {
		
		baseURI = "https://reqres.in/api";
		when()
			.delete("/users/514")
		.then()
			.statusCode(204)
			.log().all();
	}

}
