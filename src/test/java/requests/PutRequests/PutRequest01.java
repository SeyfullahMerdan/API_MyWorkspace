package requests.PutRequests;

import baseUrl.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import testData.JsonPlaceHolderTestData;

import static io.restassured.RestAssured.given;

public class PutRequest01 extends JsonPlaceHolderBaseUrl {


    @Test
    public void putRequest01() {

        // URL - spec oluşturma
        spec01.pathParams("parametre1","todos","parametre2","198");

        // Expected Oluşturma

        JsonPlaceHolderTestData obje=new JsonPlaceHolderTestData();
        JSONObject expectedRequest=obje.setUpPutData();
        System.out.println("expectedRequest = " + expectedRequest);


        // Put-Requestimi gönderiyorum.

        Response response=given()
                .contentType(ContentType.JSON)
                .spec(spec01)
                .auth().basic("admin" , "admin321")     // post ve put işlemlerinde yetkilendirme için izin alıyorum
                .body(expectedRequest.toString())      // post ve put işlemlerinde gönderiyoruz
                .when()
                .put("/{parametre1}/{parametre2}");
        response.prettyPrint();


        //======= Assertion ---->> Json PAth ile==================================

        JsonPath json=response.jsonPath();

        Assert.assertEquals(200 , response.getStatusCode());
        Assert.assertEquals( expectedRequest.getString("title") , json.getString("title") );
        Assert.assertEquals( expectedRequest.getBoolean("completed") , json.getBoolean("completed") );
        Assert.assertEquals( expectedRequest.getInt("userId") , json.getInt("userId") );




    }


}
