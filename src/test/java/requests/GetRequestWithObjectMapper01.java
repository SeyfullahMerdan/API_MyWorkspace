package requests;

import baseUrl.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import utilities.JsonUtil;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class GetRequestWithObjectMapper01 extends JsonPlaceHolderBaseUrl {

    /*
    "http://jsonplaceholder.typicode.com"
     */

    @Test
    public void test() {

        spec01.pathParams("parametre1","todos","parametre2",198 );

        String jsonData="{\n" +
                "    \"userId\": 10,\n" +
                "    \"id\": 198,\n" +
                "    \"title\": \"quis eius est sint explicabo\",\n" +
                "    \"completed\": true\n" +
                "}";

        Map<String , Object>  expectedData= JsonUtil.convertJsonToJava( jsonData , Map.class );
        System.out.println("expectedData = " + expectedData);

        Response response=given().
                contentType(ContentType.JSON)
                .spec(spec01)
                .when()
                .get("/{parametre1}/{parametre2}");

        response.prettyPrint();


        Map<String , Object>  actualData=JsonUtil.convertJsonToJava(response.asString() , Map.class);
        System.out.println("actualData = " + actualData);



        Assert.assertEquals(expectedData.get("userId") , actualData.get("userId") );
        Assert.assertEquals(expectedData.get("id") , actualData.get("id") );
        Assert.assertEquals(expectedData.get("title") , actualData.get("title") );
        Assert.assertEquals(expectedData.get("completed") , actualData.get("completed") );



        //===========================================================================

        response.then()
                .assertThat()
                .contentType(ContentType.JSON)
                .body("userId", equalTo(expectedData.get("userId")),
                        "id"   , equalTo(expectedData.get("id"))  ,
                        "title"     , equalTo(expectedData.get("title")) ,
                        "completed" , equalTo("completed")
                );


        //============================================================================




        
        

    }

}
