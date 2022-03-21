package requests.PatchDeleteRequest;

import baseUrl.DummyBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import testData.DummyTestData;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class DeleteRequest01 extends DummyBaseUrl {


    @Test
    public void DeleteRequest01() {

        // url
        spec03.pathParams("parametre1","api","parametre2","v1", "parametre3","delete" , "parametre4","2");


        // expected data
        DummyTestData obje=new DummyTestData();
        JSONObject expectedData=obje.setUpDeleteExpectedData();


        //request
        Response response=given()
                .contentType(ContentType.JSON)
                .spec(spec03)
                .auth().basic("admin" , "admin321")
                .when()   // Body yok çünkü silecem, get gibi düşünebiliriz. Sielcegim şeye body gönderemem.
                .delete("/{parametre1}/{parametre2}/{parametre3}/{parametre4}");
        response.prettyPrint();


        //Assertion



        //==========================================Matcher Class ile================================================

        response.then()
                .assertThat()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .body(  "status" , equalTo(expectedData.getString("status")),
                        "data"    , equalTo(expectedData.getString("data")),
                        "message" , equalTo(expectedData.getString("message"))
                );

        //============================================================================================================



        //======================================= Json Path ile ======================================================

        JsonPath json= response.jsonPath();

        Assert.assertEquals(expectedData.getString("status") , json.getString("status"));
        Assert.assertEquals(expectedData.getString("data") , json.getString("data"));
        Assert.assertEquals(expectedData.getString("message") , json.getString("message"));

        //============================================================================================================



        //======================================= De-Serilization ile ================================================


        HashMap<String, Object> actualData=response.as(HashMap.class);

        Assert.assertEquals(expectedData.getString("status") , actualData.get("status"));
        Assert.assertEquals(expectedData.getString("data") , actualData.get("data"));
        Assert.assertEquals(expectedData.getString("message") , actualData.get("message"));

        System.out.println(" İşlem Başarılı ");


        //============================================================================================================





    }


}
