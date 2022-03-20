package requests.GetRequests;

import baseUrl.DummyBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import testData.DummyTestData;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetRequest14JsonPath extends DummyBaseUrl {


    @Test
    public void GetRequest14() {

        // URL oluştur
        spec03.pathParams("parametre1","api","parametre2","v1", "parametre3","employees");

        // Daha sonra beklenen sonuç (expected result) oluşturulur
        DummyTestData obje = new DummyTestData();
        HashMap<String, Object> expectedData = obje.setUpTestData();
        System.out.println("expectedData = " + expectedData);

        // Request gönderiyoruz.
        Response response =given()
                .accept("application/json")
                .spec(spec03)
                .when()
                .get("/{parametre1}/{parametre2}/{parametre3}");
        response.prettyPrint();

        // Jsonpath objesi oluşturma yapıyoruz
        JsonPath json= response.jsonPath();

        Assert.assertEquals(  expectedData.get("statuscode") , response.getStatusCode()   );
        Assert.assertEquals(  expectedData.get("besincicalisaninismi") , json.getString("data[4].employee_name") );
        Assert.assertEquals(  expectedData.get("calisansayisi") , json.getList("data.id").size() );
        Assert.assertEquals(  expectedData.get("sondanikincicalisanmaas") , json.getInt("data[-2].employee_salary"));
        Assert.assertTrue(  json.getList("data.employee_age").containsAll((List) expectedData.get("yas"))  );
        Assert.assertEquals(  ((Map)expectedData.get("onbirincicalisan")).get("employee_name"),
                json.getString("data[10].employee_name")
        );

        Assert.assertEquals(  ((Map)expectedData.get("onbirincicalisan")).get("employee_salary"),
                json.getInt("data[10].employee_salary")
        );

        Assert.assertEquals(  ((Map)expectedData.get("onbirincicalisan")).get("employee_age"),
                json.getInt("data[10].employee_age")
        );

        Assert.assertEquals(  ((Map)expectedData.get("onbirincicalisan")).get("profile_image"),
                json.getString("data[10].profile_image")
        );




    }


}
