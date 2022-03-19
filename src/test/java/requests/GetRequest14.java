package requests;

import baseUrl.DummyBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import testData.DummyTestData;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class GetRequest14 extends DummyBaseUrl {


    @Test
    public void GetRequest14() {

        // URL oluştur
        spec03.pathParams("parametre1","api","parametre2","v1", "parametre3","employees");


        // Daha sonra beklenen sonuç (expected result) oluşturulur

        DummyTestData obje = new DummyTestData();
        HashMap<String, Object> expectedData = obje.setUpTestData();
        System.out.println("expectedData Map = " + expectedData);

        // Request gönderiyoruz.

        Response response =given()
                .accept("application/json")
                .spec(spec03)
                .when()
                .get("/{parametre1}/{parametre2}/{parametre3}");
        response.prettyPrint();

        // Actual Result oluşturuyoruz

        HashMap<String, Object> actualData=response.as(HashMap.class);
        System.out.println("actualData Mapi = " + actualData);

        // Dogrulama yapıyoruz.















    }

}
