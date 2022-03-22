package requests;

import baseUrl.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.TodosPojo;

import static io.restassured.RestAssured.given;

public class PostRequestWithPojo01 extends JsonPlaceHolderBaseUrl {



    @Test
    public void test01() {

        spec01.pathParams("parametre1" ,"todos" );

        TodosPojo requestExpected=new TodosPojo(21,201,"Tidy your room",false);

        System.out.println("requestExpected = " + requestExpected);

        Response response=given()
                .contentType(ContentType.JSON)
                .spec(spec01)
                .auth().basic("admin" , "admin321")
                .body(requestExpected) //zaten toStringli bir yapı geldi bu yüzden bu sfeer toString eklemedim
                .when()
                .post("/{parametre1}");

        response.prettyPrint();

        // De Serilization ile Dogrulama

        //actual data oluşturdum.

        TodosPojo actualData=response.as(TodosPojo.class);

        Assert.assertEquals( 201 , response.getStatusCode() );

        Assert.assertEquals(requestExpected.getUserId() , actualData.getUserId());
        Assert.assertEquals(requestExpected.getId() , actualData.getId());
        Assert.assertEquals(requestExpected.getTitle() , actualData.getTitle());
        Assert.assertEquals(requestExpected.isCompleted() , actualData.isCompleted());
















    }


}
