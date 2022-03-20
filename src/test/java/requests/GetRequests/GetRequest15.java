package requests.GetRequests;

import baseUrl.DummyBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import testData.DummyTestData;

import java.util.*;

import static io.restassured.RestAssured.given;

public class GetRequest15 extends DummyBaseUrl {


    @Test
    public void GetRequest15() {

        // URL oluştur
        spec03.pathParams("parametre1", "api", "parametre2", "v1", "parametre3", "employees");

        // Daha sonra beklenen sonuç (expected result) oluşturulur
        DummyTestData obje = new DummyTestData();
        HashMap<String, Object> expectedData = obje.setUpTestData15();
        System.out.println("expectedData = " + expectedData);

        // Request gönderiyoruz.
        Response response = given()
                .accept("application/json")
                .spec(spec03)
                .when()
                .get("/{parametre1}/{parametre2}/{parametre3}");
        response.prettyPrint();

        // actualData oluşturacagım.

        HashMap<String, Object> actualData = response.as(HashMap.class);
        System.out.println("actualData = " + actualData);


        // Assertionları yap

        Assert.assertEquals( expectedData.get("statuscode") , response.getStatusCode() );

        List<Integer>maaslar=new ArrayList<>();
        int dataSize= ((List)actualData.get("data")).size();
        for (int i = 0; i < dataSize ; i++) {
            maaslar.add((Integer) ((Map)((List)actualData.get("data")).get(i)).get("employee_salary"));
        }
        Collections.sort(maaslar);

        Assert.assertEquals( expectedData.get("enyuksekmaas") , maaslar.get(dataSize-1)  );
        Assert.assertEquals( expectedData.get("ikincienyuksekmaas") , maaslar.get(dataSize-2)  );

        List<Integer>yaslar=new ArrayList<>();

        for (int i = 0; i < dataSize ; i++) {
            yaslar.add((Integer) ((Map)((List)actualData.get("data")).get(i)).get("employee_age"));
        }
        Collections.sort(yaslar);

        Assert.assertEquals( expectedData.get("enkucukyas") , yaslar.get(0)  );

//======================================================================================================================
          // ============================  JsonPath ile==================================================


        JsonPath json= response.jsonPath();

        List <Integer> maasListesijs=json.getList("data.employee_salary"); // get list methodu ile
                                                         // tüm salaryleri bir liste atmış olduk
        Collections.sort(maasListesijs);

        Assert.assertEquals( expectedData.get("enyuksekmaas"), maasListesijs.get(maasListesijs.size() - 1));
        Assert.assertEquals( expectedData.get("ikincienyuksekmaas"), maasListesijs.get(maasListesijs.size() - 2));

        // Burada For işlemine girmemiş olduk, daha az ve kolay bir kod yazdık.

        List <Integer> yasListesijs=json.getList("data.employee_age");  // Yaşların hepsini bu listeye attım.
        Collections.sort(yasListesijs);

        Assert.assertEquals( expectedData.get("enkucukyas") , yasListesijs.get(0));


//======================================================================================================================







    }

}
