package requests.GetRequests;

import baseUrl.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import testData.JsonPlaceHolderTestData;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class GetRequest12DeSerilization extends JsonPlaceHolderBaseUrl {

    @Test
    public void GetRequest12DeSeril() {

        spec01.pathParams("parametre1" ,"todos", "parametre2", "2");

        JsonPlaceHolderTestData expectedObje=new JsonPlaceHolderTestData();
        HashMap<String, Object> expectedData = (HashMap<String, Object>) expectedObje.setUpTestData();

        // response oluşturuyorum.
        Response response =given()
                .accept(ContentType.JSON)
                .spec(spec01)
                .when()
                .get("/{parametre1}/{parametre2}"
                );
        response.prettyPrint();

        // 1. Yöntem Matchers class ile Assertion işlemi yaptık...
        // actual data oluşturmama gerek yok, responsedan gelen zaten actuall data oluyor.
        response.then().assertThat().statusCode((Integer) expectedData.get("statusCode"))
                .headers("via", equalTo(expectedData.get("Via")) ,
                        "Server" , equalTo(expectedData.get("Server")))
                .body("userId" , equalTo(expectedData.get("userId")),
                        "title" , equalTo(expectedData.get("title")),
                        "completed" , equalTo(expectedData.get("completed"))
                );

        // 2. Yöntem Jsonpath ile yapıyoruz...!!!!!
        JsonPath json= response.jsonPath();

        Assert.assertEquals( expectedData.get("statusCode") , response.statusCode());//header ve digerleri jsonpathsiz..!
        Assert.assertEquals( expectedData.get("Via") , response.getHeader("via"));//Bu degerleri almak için response
        Assert.assertEquals( expectedData.get("Server") , response.getHeader("Server"));//kullanmak zorundayım

        Assert.assertEquals( expectedData.get("userId") , json.getInt("userId"));  //Body ögeleri jsonpath ile
        Assert.assertEquals( expectedData.get("title") , json.getString("title"));
        Assert.assertEquals( expectedData.get("completed") , json.getBoolean("completed") );


        // 3. Yöntem deserilization ile yapıyoruz...!!!!!


        HashMap<String,Object>actualData=response.as(HashMap.class); //HashMap Javadan bir Class.
        System.out.println("actualData Map= " + actualData);
        // responsedan gelen datayı Map gibi al , actualDataya ata demiş oluyoruz as(HAshMap) ile...
        // =========== Bu satırda De-Serilization işlemini gerçekleştirmiş olduk====================

        Assert.assertEquals(expectedData.get("userId"), actualData.get("userId"));
        Assert.assertEquals(expectedData.get("titel"), actualData.get("titel"));
        Assert.assertEquals(expectedData.get("completed"), actualData.get("completed"));


    }

}
