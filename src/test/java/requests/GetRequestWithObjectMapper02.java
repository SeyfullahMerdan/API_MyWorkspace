package requests;

import baseUrl.HerokuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import utilities.JsonUtil;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class GetRequestWithObjectMapper02 extends HerokuAppBaseUrl {
    /*
 https://restful-booker.herokuapp.com/booking/2 url’ine bir get request gönderildiğinde,
 status kodun 200 ve response body’nin
{
    "firstname": "Eric",
    "lastname": "Jackson",
    "totalprice": 751,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2020-02-10",
        "checkout": "2021-03-19"
    },
    "additionalneeds": "Breakfast"
}

Olduğunu Object Mapper kullanarak test edin.
*/

    @Test
    public void Test() {

        spec02.pathParams("parametre1" , "booking", "parametre2" , 2 );

        String jsonData="{\n" +
                "    \"firstname\": \"Eric\",\n" +
                "    \"lastname\": \"Jackson\",\n" +
                "    \"totalprice\": 751,\n" +
                "    \"depositpaid\": true,\n" +
                "    \"bookingdates\": {\n" +
                "        \"checkin\": \"2020-02-10\",\n" +
                "        \"checkout\": \"2021-03-19\"\n" +
                "    },\n" +
                "    \"additionalneeds\": \"Breakfast\"\n" +
                "}\n";

        HashMap<String, Object> expectedData=JsonUtil.convertJsonToJava(jsonData , HashMap.class);
        System.out.println("expectedData = " + expectedData);


        Response response =given()
                .contentType(ContentType.JSON)
                .spec(spec02)
                .when()
                .get("/{parametre1}/{parametre2}");

        response.prettyPrint();

        
        HashMap<String, Object> actualData=JsonUtil.convertJsonToJava(response.asString() , HashMap.class);
        System.out.println("actualData = " + actualData);


        Assert.assertEquals(expectedData.get("firstname") , actualData.get("firstname"));
        Assert.assertEquals(expectedData.get("lastname") , actualData.get("lastname"));
        Assert.assertEquals(expectedData.get("totalprice") , actualData.get("totalprice"));
        Assert.assertEquals(expectedData.get("depositpaid") , actualData.get("depositpaid"));
        Assert.assertEquals( ((Map)expectedData.get("bookingdates")).get("checkin") ,
                             ((Map)actualData.get("bookingdates")).get("checkin")
        );

        Assert.assertEquals( ((Map)expectedData.get("bookingdates")).get("checkout") ,
                             ((Map)actualData.get("bookingdates")).get("checkout")
        );


        //========================================================================================

        response.then()
                .assertThat().statusCode(200)
                .body( "firstname" , equalTo(expectedData.get("firstname")) ,
                        "lastname" , equalTo(expectedData.get("lastname")) ,
                        "totalprice" , equalTo(expectedData.get("totalprice")),
                        "depositpaid" ,equalTo(expectedData.get("depositpaid"))
                );


        //==========================================================================================










    }


}
