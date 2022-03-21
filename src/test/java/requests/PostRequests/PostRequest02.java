package requests.PostRequests;

import baseUrl.HerokuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import testData.HerokuAppTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class PostRequest02 extends HerokuAppBaseUrl {

    /*
    "booking": {
        "firstname": "seyfo",
        "lastname": "reis",
        "totalprice": 654,
        "depositpaid": true,
        "bookingdates": {
            "checkin": "2022-11-22",
            "checkout": "2022-11-11"
        }
    }
     */

    @Test
    public void postTest02(){

        // URL oluşturuyoruz

        spec02.pathParams("parametre1","booking" );

        // requestBody ve expectedData aynı oldugu için tek bir JSONObject kullanılması yeterlidir.

        HerokuAppTestData obje=new HerokuAppTestData();
        JSONObject expectedRequestData=obje.setUpTestAndRequestData();

        // Request oluşturuyoruz.

        Response response=given()
                .contentType("application/json")
                .spec(spec02)
                .auth()
                .basic("admin" , "admin321")
                .body(expectedRequestData.toString())
                .when()
                .post("/{parametre1}");

        response.prettyPrint();


    //=================================== De-Serilization ile Assertion ===============================================

        // Actual data oluşturacam

        HashMap<String, Object> actualData = response.as(HashMap.class);
        System.out.println("actualData = " + actualData);

        Assert.assertEquals( expectedRequestData.getString("firstname") ,
                ((Map)actualData.get("booking")).get("firstname")        );


        Assert.assertEquals(expectedRequestData.getString("lastname"),
                ((Map) actualData.get("booking")).get("lastname") );


        Assert.assertEquals(expectedRequestData.getInt("totalprice") ,
           ((Map) actualData.get("booking")).get("totalprice")) ;


        Assert.assertEquals(expectedRequestData.getBoolean("depositpaid") ,
                ((Map) actualData.get("booking")).get("depositpaid")) ;


        Assert.assertEquals( expectedRequestData.getJSONObject("bookingdates").getString("checkin") ,
                ((Map)((Map) actualData.get("booking")).get("bookingdates")).get("checkin"));



        Assert.assertEquals( expectedRequestData.getJSONObject("bookingdates").getString("checkout"),
                ((Map)((Map) actualData.get("booking")).get("bookingdates")).get("checkout")
        );



      //=================================== JSonPath ile Dogrulama=====================================================

        JsonPath json= response.jsonPath();

        Assert.assertEquals( expectedRequestData.getString("firstname"),json.getString("booking.firstname")   );
        Assert.assertEquals( expectedRequestData.getString("lastname"),json.getString("booking.lastname")   );
        Assert.assertEquals( expectedRequestData.getInt("totalprice"),json.getInt("booking.totalprice")   );
        Assert.assertEquals( expectedRequestData.getBoolean("depositpaid"),json.getBoolean("booking.depositpaid")   );

        Assert.assertEquals( expectedRequestData.getJSONObject("bookingdates").getString("checkin") ,
                json.getBoolean("booking.bookingdates.checkin")
        );


        Assert.assertEquals( expectedRequestData.getJSONObject("bookingdates").getString("checkout") ,
                json.getBoolean("booking.bookingdates.checkout")
        );


    //=================================================================================================================




    }


}
