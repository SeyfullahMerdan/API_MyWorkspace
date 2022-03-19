package requests;

import baseUrl.HerokuAppBaseUrl;
import baseUrl.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import testData.HerokuAppTestData;
import testData.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetRequest13 extends HerokuAppBaseUrl {



    @Test
    public void GetRequest13() {

        // 1 URL oluştur
        spec02.pathParams("parametre1","booking", "parametre2","6");

        // 2 Request gönderelim
        Response response =given()
                .accept("application/json")
                .spec(spec02)
                .when()
                .get("/{parametre1}/{parametre2}");
        response.prettyPrint();

        // 3-) Daha sonra beklenen sonuç (expected result) oluşturulur.
        // oluşturdugum expected datayı buraya aldım obje yardımı ile

        HerokuAppTestData herokuapp=new HerokuAppTestData();
        HashMap<String, Object> expectedData= (HashMap<String, Object>) herokuapp.setUpTestData();
        System.out.println("expectedData = " + expectedData);

        // =============================== De-Serilization ile =====================================================

        // 4-) Actual Result oluşturuyoruz

        HashMap<String, Object> actualData=response.as(HashMap.class); // de-serilization  json->java...
        System.out.println("actualData = " + actualData);

        // 5-) Dogrulama yapıyoruz.

        Assert.assertEquals(expectedData.get("firstname") , actualData.get("firstname"));
        Assert.assertEquals(expectedData.get("lastname") , actualData.get("lastname"));
        Assert.assertEquals(expectedData.get("totalprice") , actualData.get("totalprice"));
        Assert.assertEquals(expectedData.get("depositpaid") , actualData.get("depositpaid"));
        Assert.assertEquals(  ((Map)expectedData.get("bookingdates")).get("checkin") ,
                ((Map)actualData.get("bookingdates")).get("checkin")
        );

        Assert.assertEquals(  ((Map) expectedData.get("bookingdates")).get("checkout") ,
                ((Map) actualData.get("bookingdates")).get("checkout")
        );



        // =============================== Json PAth ile =====================================================

        JsonPath json=response.jsonPath();

        Assert.assertEquals( expectedData.get("firstname") , json.getString("firstname")  );
        Assert.assertEquals( expectedData.get("lastname") , json.getString("lastname")  );
        Assert.assertEquals( expectedData.get("totalprice") , json.getInt("totalprice")  );
        Assert.assertEquals( expectedData.get("depositpaid") , json.getBoolean("depositpaid")  );

        Assert.assertEquals( ((Map) expectedData.get("bookingdates")).get("checkin")
                                             , json.getString("bookingdates.checkin")
        );

        Assert.assertEquals( ((Map) expectedData.get("bookingdates")).get("checkout")
                               ,  json.getString("bookingdates.checkout")
        );



    }

}
