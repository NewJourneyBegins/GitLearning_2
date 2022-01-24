package restassuredtestcases;
import org.testng.annotations.Test;
import io.restassured.filter.log.LogDetail;

//imp - static
import static io.restassured.RestAssured.*;

//imp for equalsto and all
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class GetRequestCucumberStyle {

	@Test
	public void testGetUsersList() {

		//in api we have URI(Uniform resource identifier) and not url
		baseURI = "https://reqres.in/api";

		given()
			.get("/users?page=2")
		.then()
			.statusCode(200)
			.log().all();

	}

	@Test
	public void testValidateGetUserlistData() {
		baseURI = "https://reqres.in/api";

		given()
			.get("/users?page=2")
		.then()
			.statusCode(200)
			.body("data[2].id", equalTo(9))
			.body("data[0].first_name", equalTo("Michael"))
			.body("data.first_name", hasItem("Tobias"))
			.body("data.first_name", hasItems("Michael","Lindsay","Byron"))
			.log().body()
			.log().headers()
			.log().everything()
			.log().status()
			.log().ifStatusCodeIsEqualTo(201)
			.log().ifStatusCodeIsEqualTo(200)
			
			//if failure is there then log specific information using below code
//			.body("data[2].id", equalTo(8))//failing purposly
//			.log().ifValidationFails(LogDetail.STATUS)
			
			.log().all();
		
	}
	
	@Test
	public void testGetSingleUserData() {

		//in api we have URI(Uniform resource identifier) and not url
		baseURI = "https://reqres.in/api";

		given()
			.get("/users/2")
		.then()
			.statusCode(200)
			.body("data.id", equalTo(2))
			.log().all();

	}
	
	@Test
	public void testValidateGetUserNotFound() {
		baseURI = "https://reqres.in/api";

		given()
			.get("/users/23")
		.then()
			.statusCode(404)
			.log().ifStatusCodeIsEqualTo(404);	
	}
	
	@Test
	public void testValidateGetListResourceData() {
		baseURI = "https://reqres.in/api";

		given()
			.get("/unknown")
		.then()
			.statusCode(200)
			.body("data[2].id", equalTo(3))
			.body("data.year", hasItems(2000,2001,2002,2003,2004,2005))
			//log response body
			.log().body()
			//log response headers
			.log().headers()
			//log response status
			.log().status();
			
	}
	
	@Test
	public void testValidateGetSingleResourceData() {
		baseURI = "https://reqres.in/api";

		given()
			.get("/unknown/2")
		.then()
			.statusCode(200)
			.body("data.id", equalTo(2))
			.body("data.name", equalTo("fuchsia rose"))
			//we are logging everything
			.log().everything();	
	}
	
	@Test
	public void testValidateGetSingleUserNotFound() {
		baseURI = "https://reqres.in/api";

		given()
			.get("/unknown/23")
		.then()
			.statusCode(404)
			//validation id status code is equal to 404 then log details
			.log().ifStatusCodeIsEqualTo(404);	
	}
	

}
