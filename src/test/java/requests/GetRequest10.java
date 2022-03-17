package requests;

import baseUrl.DummyBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class GetRequest10 extends DummyBaseUrl {

    @Test
    public void test10() {


        spec03.pathParams("parametre1","api","parametre2","v1", "parametre3","employees");

        Response response=given()
                .accept(ContentType.JSON)
                .spec(spec03)
                .when()
                .get("/{parametre1}/{parametre2}/{parametre3}");
        response.prettyPrint();


        JsonPath json=response.jsonPath();

        Assert.assertEquals(200, response.statusCode());
        Assert.assertTrue(200 == response.statusCode());

        Assert.assertEquals("Airi Satou" , json.getString("data.employee_name[4]"));
        Assert.assertEquals(372000, json.getInt("data.employee_salary[5]"));
        Assert.assertEquals(24, json.getList("data.id").size());

        Assert.assertTrue(json.getString("data.employee_name").contains("Rhona Davidson"));


        // List<Integer> arananyaslar=Arrays.asList(21,23,61);
        List<Integer> arananyaslar=new ArrayList<>();
        arananyaslar.add(21);
        arananyaslar.add(23);
        arananyaslar.add(61);

        Assert.assertTrue( json.getList("data.employee_age").containsAll(arananyaslar)  );
        // expected datalarımı bir liste attım daha sonra jsonpath ile actual datayı getirerek
        // expected listemi kontrol ettim.





    }


}
