package requests.GetRequests;

import baseUrl.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class GetRequest12 extends JsonPlaceHolderBaseUrl {


    @Test
    public void GetRequest12() {

        spec01.pathParams("parametre1" ,"todos", "parametre2", "2");

        // Expected Data oluşturdum.

        HashMap<String, Object> expectedData=new HashMap<String, Object>();

        expectedData.put("statusCode" , 200);
        expectedData.put("Via" , "1.1 vegur");
        expectedData.put("Server" , "cloudflare");
        expectedData.put("userId" , 1);
        expectedData.put("title" , "quis ut nam facilis et officia qui");
        expectedData.put("completed" , false);
        System.out.println("expectedData Map Listesi= " + expectedData);
        // Amacım bu classımda herhangi bir data olmaması. Önce mape koyuyorum
        // daha sonra bu Mapi farklı bir classda oluşturacam
        // Farklı classtan çagıracam

        // response oluşturuyorum.
        Response response =given()
                .accept(ContentType.JSON)
                .spec(spec01)
                .when()
                .get("/{parametre1}/{parametre2}"
                );
        response.prettyPrint();

        // actual data oluşturmama gerek yok, responsedan gelen zaten actuall data oluyor.
        response.then().assertThat().statusCode((Integer) expectedData.get("statusCode"))
        // status codeun içi integer kabul ediyor ama benim Mapimden object gelecegi için java kabul etmiyor
        // Bu yüzden içerisinden gelecek olanın integer olacagını garanti etmek için casting yapıyorum.
                .headers("via", equalTo(expectedData.get("Via")) ,
                        "Server" , equalTo(expectedData.get("Server")))
                .body("userId" , equalTo(expectedData.get("userId")),
                       "title" , equalTo(expectedData.get("title")),     // Matcherda ilk taraf actuell oluyor.
                       "completed" , equalTo(expectedData.get("completed"))
                );
        // 1. Yöntem Matchers class ile Assertion işlemi yaptık...

        // 2. Yöntem Jsonpath ile yapıyoruz...!!!!!

        JsonPath json= response.jsonPath();

        Assert.assertEquals( expectedData.get("statusCode") , response.statusCode());//header ve digerleri jsonpathsiz..!
        Assert.assertEquals( expectedData.get("Via") , response.getHeader("via"));//Bu degerleri almak için response
        Assert.assertEquals( expectedData.get("Server") , response.getHeader("Server"));//kullanmak zorundayım

        Assert.assertEquals( expectedData.get("userId") , json.getInt("userId"));  //Body ögeleri jsonpath ile
        Assert.assertEquals( expectedData.get("title") , json.getString("title"));
        Assert.assertEquals( expectedData.get("completed") , json.getBoolean("completed") );

        // 2. Yöntem Jsonpath ile yaptık...!!!!!


        // 3. Yöntem deserilization ile yapıyoruz...!!!!!
        // Object Mapper
        // POJO class

    }

}
