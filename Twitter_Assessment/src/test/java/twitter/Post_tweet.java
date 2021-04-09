package twitter;

import static io.restassured.RestAssured.given;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Post_tweet {
	Properties p = new Properties();
	
	private static Logger log = LogManager.getLogger(getTrends.class.getName());

	@Test 
	public void API_test1() throws IOException {
		
		
		FileInputStream fis = new FileInputStream("C:\\Twitter_Assessment\\src\\main\\java\\resources\\data.properties");
		p.load(fis);
	RestAssured.baseURI="https://api.twitter.com/1.1/statuses/";
	Response res =
	given().auth().oauth(p.getProperty("CONSUMER_KEY"),p.getProperty("CONSUMER_SECRET"),p.getProperty("ACCESS_TOKEN"),p.getProperty("TOKEN_KEY")).
	queryParam("status","I am learning API testing using RestAssured Java#Qualitest").
	when().
		post("update.json").
		
	then().assertThat().statusCode(200).extract().response();
	
	String responseString = res.asString() ;
	JsonPath js = new JsonPath(responseString);
	long abd = js.get("id") ;
	System.out.println(abd);
	if (abd != 0) {
		log.info("TWEET CREATED!!");
	}
	
			}
	
}
