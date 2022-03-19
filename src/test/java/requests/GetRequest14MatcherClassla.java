package requests;

import baseUrl.DummyBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import testData.DummyTestData;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class GetRequest14MatcherClassla extends DummyBaseUrl {

    @Test
    public void GetRequest15() {

        // URL oluştur
        spec03.pathParams("parametre1", "api", "parametre2", "v1", "parametre3", "employees");

        // Response oluştur

        Response response =given()
                .accept("application/json")
                .spec(spec03)
                .when()
                .get("/{parametre1}/{parametre2}/{parametre3}");
        response.prettyPrint();

        //

        DummyTestData obje = new DummyTestData();
        HashMap<String, Object> expectedData = obje.setUpTestData();
        System.out.println("expectedData Map = " + expectedData);

       // response.then().assertThat().statusCode(expectedData.get("statuscode"))
         //       .body()












    }
}
