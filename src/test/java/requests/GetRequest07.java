package requests;

import baseUrl.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class GetRequest07 extends JsonPlaceHolderBaseUrl {

    // http://jsonplaceholder.typicode.com/todos/123

    @Test
    public void test07() {

       // String url = "http://jsonplaceholder.typicode.com/todos/123";

        spec01.pathParams("parametre1" , "todos" , "parametre2" , 123); // Url üzerine pathparamı ekledim.

        Response response=given().
                accept("application/json"). //Eğer API birden fazla data typeini döndürüyorsa o zaman belirtiyoruz
                spec(spec01).
                when().
                get("/{parametre1}/{parametre2}");
          // parametre1 veya 2'yi almıyorum, bunlara karşılık gelen değerleri alıyorum,bu yüzden {} kullanıyorum

        response.prettyPrint();

        response.then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .header("Server" , equalTo("cloudflare"))
                .body("userId" ,equalTo(7) ,
                        "title" , equalTo("esse et quis iste est earum aut impedit"),
                        "completed" , equalTo(false)
                );



    }

}
