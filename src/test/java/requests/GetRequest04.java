package requests;

import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;  // Bütün classları import ettim

import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest04 {


    /*
    https://restful-booker.herokuapp.com/booking/7
    {
    "firstname": "Susan",
    "lastname": "Wilson",
    "totalprice": 613,
    "depositpaid": false,
    "bookingdates": {
        "checkin": "2020-08-25",
        "checkout": "2021-07-10"
    }
     */

    @Test
    public void test03(){

        String url="https://restful-booker.herokuapp.com/booking/7";
        Response response=given().accept("application/json").when().get(url);
        response.prettyPrint();
        response.then().assertThat().statusCode(200).contentType("application/json")
                .body("firstname" , Matchers.equalTo("Susan"))
                .body("lastname" , Matchers.equalTo("Wilson"))
                .body("totalprice" , Matchers.equalTo(613))
                .body("depositpaid" , Matchers.equalTo(false))
                .body("bookingdates.checkin" , Matchers.equalTo("2020-08-25") )
                .body("bookingdates.checkout" , Matchers.equalTo("2021-07-10") );

        // API da dogrulamayı Matcher class ile de yapabiliyoruz.
        // ilk kısım responsedan gelen kısım, ikinci kısım ise expected olan kısımdır.
        // Daha dinamik ve tekrarsız bir kod yazmak için bu kodları tek bodyde sadeleştirebiliriz.

        response.then()
                .assertThat()
                .statusCode(200)
                .contentType("application/json")
                .body( "firstname" , equalTo("Susan") ,
                        "lastname" , equalTo("Wilson"),
                        "totalprice" , equalTo(613),
                        "depositpaid" , equalTo(false),
                        "bookingdates.checkin" ,equalTo("2020-08-25"),
                        "bookingdates.checkout" , equalTo("2021-07-10")
                );







    }


}
