package requests.GetRequests;

import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest06 {

    // http://dummy.restapiexample.com/api/v1/employees

    @Test
    public void test06() {

        String url = "http://dummy.restapiexample.com/api/v1/employees";
        Response response=given().accept("application/json").when().get(url);
        response.prettyPrint();

        response.then()
                .assertThat()
                .statusCode(200)
                .contentType("application/json")
                .body( "data" , Matchers.hasSize(24),// data.id diyerek id'lerin sayısını alarakta size'i ögrenebilirim.
                        "data.employee_name" , Matchers.hasItem("Ashton Cox"), // ilk ifadeye jsonpath diyoruz!!
                        "data.employee_age" , Matchers.hasItems(21,61,23)
                //Actual: <[61, 63, 66, 22, 33, 61, 59, 55, 39, 23, 30, 22, 36, 43, 19, 66, 64, 59, 41, 35, 30, 40, 21, 23]>
                );




    }

}
