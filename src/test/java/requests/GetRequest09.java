package requests;

import baseUrl.DummyBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest09 extends DummyBaseUrl {

    @Test
    public void test09() {

        spec03.pathParams("parametre1","api","parametre2","v1", "parametre3","employees");

        Response response=given().
                accept(ContentType.JSON).
                spec(spec03).
                when().
                get("/{parametre1}/{parametre2}/{parametre3}");

        // response.prettyPrint();

        JsonPath json= response.jsonPath();

        System.out.println("employee_names getList ile: " + json.getList("data.employee_name"));
        System.out.println("employee_names getString ile: " + json.getString("data.employee_name"));

        System.out.println(json.getString("data[2].employee_name"));  // Daha dogru kullanımdır.
        System.out.println(json.getString("data.employee_name[2]"));

        System.out.println(json.getString("data.employee_name[0,1,2,3,4]")); // ilk bes adet datayı yazacak
        System.out.println(json.getString("data.employee_name[-1]"));  //sondaki datayı yazacak
        // datalar içerisinde index ile dolaşıyoruz


        Assert.assertEquals("Ashton Cox" , json.getString("data[2].employee_name"));
        Assert.assertEquals("Doris Wilder" , json.getString("data[-1].employee_name"));
        Assert.assertEquals(200, response.getStatusCode());



    }


}
