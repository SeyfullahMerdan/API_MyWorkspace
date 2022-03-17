package requests;

import baseUrl.DummyBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;

public class Request10 extends DummyBaseUrl {

     /*
http://dummy.restapiexample.com/api/v1/employees  url'ine
 GET request'i yolladigimda gelen response'un
 status kodunun 200 ve content type'inin "application/json"
ve employees sayisinin 24
ve employee'lerden birinin "Ashton Cox"
ve gelen yaslar icinde 21, 61, ve 23 degerlerinden birinin oldugunu test edin.
 */

    @Test
    public void test01() {

        spec03.pathParams("1","api","2","v1","3","employees");

        Response response=given().contentType(ContentType.JSON).spec(spec03).when().get("/{1}/{2}/{3}");
        response.prettyPrint();

        JsonPath json= response.jsonPath();   // Sadece Body var


        response.then().assertThat().statusCode(200).contentType("application/json");
           // bodydeki dataların sayısını,sizeini getirdiç.
        response.then().assertThat().body("data" , hasSize(24) ,
                                          "data.employee_name" , Matchers.hasItem("Ashton Cox"),
                                           "data.employee_age" , Matchers.hasItems(21, 6, 23)  );


    }




}
