package requests;

import baseUrl.HerokuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest08 extends HerokuAppBaseUrl {

    // jsonpath --> bir assertion yöntemi

    // https://restful-booker.herokuapp.com/booking/16 adresine gidilir


    @Test
    public void test08() {

        spec02.pathParams("parametre1","booking","parametre2",16);

        Response response=given().
                accept("application/json").
                spec(spec02).
                when().get("/{parametre1}/{parametre2}");

        response.prettyPrint();

        // Assert.assertEquals(200, response.statusCode());  önce expected sonra actual çünkü JUnit!!

        JsonPath json=response.jsonPath(); // jsonpath bize sadece body döndürür. Headers döndürmez!

        Assert.assertEquals("Marisja" , json.getString("firstname"));
        Assert.assertEquals("Van der Wiel" , json.getString("lastname"));
        Assert.assertEquals(384 , json.getInt("totalprice"));
        Assert.assertEquals(false , json.getBoolean("depositpaid"));
        Assert.assertEquals("2022-03-15" , json.getString("bookingdates.checkin"));
        Assert.assertEquals("2022-03-22" , json.getString("bookingdates.checkout"));
        // Json pathden dolayı böyle yazdım
        // bir şeyin alt childina ulaşmak için nokta koymamız gerekiyor.




    }


}
