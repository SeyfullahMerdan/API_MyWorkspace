package requests.GetRequests;

import baseUrl.DummyBaseUrl;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Test;
import testData.DummyTestData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

public class GetRequest14MatcherClass extends DummyBaseUrl {

    @Test
    public void GetRequest15() {

        // URL oluştur
        spec03.pathParams("parametre1", "api", "parametre2", "v1", "parametre3", "employees");

        // Response oluştur

        Response response = given()
                .accept("application/json")
                .spec(spec03)
                .when()
                .get("/{parametre1}/{parametre2}/{parametre3}");
        response.prettyPrint();

        //

        DummyTestData obje = new DummyTestData();
        HashMap<String, Object> expectedData = obje.setUpTestData();
        System.out.println("expectedData Map = " + expectedData);

        response.then().assertThat().statusCode((Integer) expectedData.get("statuscode"))
                .body("data[4].employee_name", equalTo(expectedData.get("besincicalisaninismi")),
                        "data.id", hasSize((Integer) expectedData.get("calisansayisi")),
                        "data[-2].employee_salary", equalTo(expectedData.get("sondanikincicalisanmaas")),
                        "data.employee_age", hasItems(((List) expectedData.get("yas")).get(0),
                                ((List) expectedData.get("yas")).get(1),
                                ((List) expectedData.get("yas")).get(2)),
                        "data[10].employee_name", equalTo(((Map) expectedData.get("onbirincicalisan")).get("employee_name")),
                        "data[10].employee_salary", equalTo(((Map) expectedData.get("onbirincicalisan")).get("employee_salary")),
                        "data[10].employee_age", equalTo(((Map) expectedData.get("onbirincicalisan")).get("employee_age")),
                        "data[10].profile_image", equalTo(((Map) expectedData.get("onbirincicalisan")).get("profile_image"))
                );

        // Matcher classda body içerisinde önce ACTUELL yazııyor !!!


    }

}