package requests;

import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;

public class GetRequest05 {

    // "https://restful-booker.herokuapp.com/booking/7"
    // {
    //    "firstname": "Mark",
    //
    //    "totalprice": 967,
    //
    //    "bookingdates": {
    //        "checkin": "2021-10-08",
    //        "checkout": "2021-12-07"
    //    }



    @Test
    public void test05(){

        String url="https://restful-booker.herokuapp.com/booking/5";

        Response response=given().accept("application/json").when().get(url);

        response.prettyPrint();

        response.then()
                .assertThat()
                .statusCode(200)
                .contentType("application/json")
                .body(  "firstname" , equalTo("Mark"),
                        "totalprice" , equalTo(998),
                        "bookingdates.checkin" , equalTo("2015-08-10"),
                        "bookingdates.checkout" , equalTo("2018-02-27")
                );














    }


}
