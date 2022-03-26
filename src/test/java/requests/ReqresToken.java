package requests;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class ReqresToken {

    // Get işleminde sıkıntı yok ancak update işleminde token gerekecektir.
    // post put fetch delete işlemlerinde bu methodu oluşturup kolaylıkla tokeni alıp
    // alınan token ile işlem yapmış olurum.

    public String tokenAl(){

        String url="https://reqres.in/api/login";

        HashMap<String,String> requestBody = new HashMap<String,String>();

        requestBody.put("email" , "eve.holt@reqres.in");
        requestBody.put("password" , "cityslicka");

        // System.out.println("requestBody = " + requestBody);

        Response response =given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(url);


        JsonPath json= response.jsonPath();
        String token = json.getString("token");



        return token;

    }


}
