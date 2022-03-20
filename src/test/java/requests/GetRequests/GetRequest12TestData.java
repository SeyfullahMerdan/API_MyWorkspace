package requests.GetRequests;

import testData.JsonPlaceHolderTestData;
import baseUrl.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class GetRequest12TestData extends JsonPlaceHolderBaseUrl{

    // Manuel olarak yazılmış bir test datası görmek istemiyorum o yüzden manuel yazılanları dışarı alacgım.
    // Bu classda bulunan expectedData Mapini başka packagee alacam.

    @Test
    public void GetRequest12Test() {

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
        // POJO class ile Map kullanarak

    }


}
