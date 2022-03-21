package requests.PostRequests;

import baseUrl.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import testData.JsonPlaceHolderTestData;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class PostRequest03 extends JsonPlaceHolderBaseUrl {


    @Test
    public void postTest03(){

        // URL
        spec01.pathParam("parametre1","todos");

        // ExpectedData oluştur
        JsonPlaceHolderTestData obje=new JsonPlaceHolderTestData();
        JSONObject expectedData = obje.setUpTestAndRequestData(); // Json object oldugu için Json object kabul etti.
        System.out.println("expectedData = " + expectedData);

        //Response oluştur
        Response response=given()
                .contentType(ContentType.JSON)
                .spec(spec01)
                .auth()    // ***
                .basic("admin" , "admin321")  // ***
                .body(expectedData.toString())    // ***
                .when()
                .post("/{parametre1}");

        response.prettyPrint();


        //=============== actuelData ve Assertion ====================================================================
        //================================== De-Serilization ile ===============================================

        HashMap<String, Object> actualData=response.as(HashMap.class);
        System.out.println("actualData = " + actualData);

        Assert.assertEquals( expectedData.getInt("userId")
                , actualData.get("userId")
        );

        Assert.assertEquals( expectedData.getString("title")
                , actualData.get("title")
        );

        Assert.assertEquals( expectedData.getBoolean("completed")
                , actualData.get("completed")
        );

         //============================================================================================================
        //================================== Matcher Class ile ===============================================

        response.then()
                .assertThat()
                .statusCode(expectedData.getInt("statuscode"))
                .body( "completed" , equalTo(expectedData.getBoolean("completed")),
                        "title"  , equalTo(expectedData.getString("title")),
                        "userId" , equalTo(expectedData.getInt("userId"))
                );


        //=============================================================================================================

        //================================== JsonPath ile ===============================================


        JsonPath json=response.jsonPath();

        Assert.assertEquals( expectedData.getInt("statuscode") , response.getStatusCode()    );
        Assert.assertEquals( expectedData.getInt("userId") , json.getInt("userId")   );
        Assert.assertEquals( expectedData.getString("title") , json.getString("title")   );
        Assert.assertEquals( expectedData.getBoolean("completed") , json.getBoolean("completed")   );


        //================================================================================================




    }

}
