package requests.GetRequests;

import baseUrl.DummyBaseUrl;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import testData.DummyTestData;

import java.util.*;

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

        // Actual Result oluşturuyoruz

        HashMap<String, Object> actualData=response.as(HashMap.class);
        System.out.println("actualData Mapi = " + actualData);


        // Dogrulama yapıyoruz.

        Assert.assertEquals(  expectedData.get("statuscode") , response.getStatusCode()  );

        Assert.assertEquals(  expectedData.get("besincicalisaninismi"),
                ((Map)((List)actualData.get("data")).get(4)).get("employee_name")
        );

        Assert.assertEquals( expectedData.get("calisansayisi") ,  ((List) actualData.get("data")).size() );


        // Önce actual datadan bize dönen listin sizeini almalıyız.
        int dataIndex=((List<?>) actualData.get("data")).size();
        Assert.assertEquals( expectedData.get("sondanikincicalisanmaas"),
                ((Map)((List<?>) actualData.get("data")).get(dataIndex-2)).get("employee_salary")
        );
        // Kodumuz dinamik olsun idye öncesinde bir konteynıra assign ettik.
        // normalde direk 22 de yazabilirdik fakat kodumuz dinamik olmazdı...


        List<Integer> actualYasListesi=new ArrayList<Integer>();
        for (int i = 0; i < dataIndex; i++) {
            actualYasListesi.add((Integer)((Map)((List) actualData.get("data")).get(i)).get("employee_age"));
        }
        Assert.assertTrue(actualYasListesi.containsAll((List) expectedData.get("yas")));


        Assert.assertEquals(
                ((Map)expectedData.get("onbirincicalisan")).get("employee_name") ,
                ((Map) ((List<?>) actualData.get("data")).get(10)).get("employee_name")
        );

        Assert.assertEquals(
                ((Map)expectedData.get("onbirincicalisan")).get("employee_salary") ,
                ((Map) ((List<?>) actualData.get("data")).get(10)).get("employee_salary")
        );

        Assert.assertEquals(
                ((Map)expectedData.get("onbirincicalisan")).get("employee_age") ,
                ((Map) ((List<?>) actualData.get("data")).get(10)).get("employee_age")
        );

        Assert.assertEquals(
                ((Map<?, ?>) expectedData.get("onbirincicalisan")).get("id") ,
                ((Map) ((List<?>) actualData.get("data")).get(10)).get("id")
        );

        Assert.assertEquals(
                ((Map<?, ?>) expectedData.get("onbirincicalisan")).get("profile_image"),
                ((Map) ((List<?>) actualData.get("data")).get(10)).get("profile_image")
        );




    }

}
