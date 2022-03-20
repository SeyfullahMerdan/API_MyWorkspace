package requests.GetRequests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest02 {

    // Body gerekemdigi zamanlarda expected data oluşturmuyorum. Body işin içine girince expected data oluşturuyorum


    @Test
    public void test02(){

        String url="https://restful-booker.herokuapp.com/booking";

        Response response=given().accept(ContentType.JSON).when().get(url);

        response.prettyPrint();

        // response classından Gherkin language ile
        response.
                then().
                assertThat().
                statusCode(200).
                contentType("application/json");

        // Hard Assertion ile
        Assert.assertEquals(200, response.statusCode());
        Assert.assertEquals("application/json; charset=utf-8", response.getContentType());



    }

}
