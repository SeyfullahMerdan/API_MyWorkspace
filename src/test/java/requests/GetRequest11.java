package requests;

import baseUrl.DummyBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class GetRequest11 extends DummyBaseUrl {

    @Test
    public void test11() {

        spec03.pathParams("parametre1","api","parametre2","v1", "parametre3","employees");

        Response response=given()
                .accept(ContentType.JSON)
                .spec(spec03)
                .when()
                .get("/{parametre1}/{parametre2}/{parametre3}");

        response.prettyPrint();

        JsonPath json= response.jsonPath();

        Assert.assertEquals(200, response.getStatusCode());

        // Groovly dili ile APIde şartlı sorgular yapabiliriz. Javanın bir alt dili gibidir.


        List<Integer>idList=json.getList( "data.findAll{it.id>10}.id" );
        System.out.println("idList = " + idList);












    }

}
