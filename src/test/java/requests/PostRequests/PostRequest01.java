package requests.PostRequests;

import baseUrl.DummyBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import testData.DummyTestData;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class PostRequest01 extends DummyBaseUrl {

    // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    // Get işlemlerinde hep getirdik. Burada ise göndemre işlemi yapacagız.
    // Post işlemi yaparken iki tane yapı oluşturmamız gerekecek, bir tanesi gönderecegimiz body
    // digeri ise gönderdigimiz body girmiş mi diye getirip bakacagımız response... !!!!!!!!!!!!!!!!!!!!!!


    @Test
    public void postTest01(){

        spec03.pathParams("parametre1","api","parametre2","v1", "parametre3","create");

        DummyTestData obje = new DummyTestData();

        // post request yaparken body göndermek zorundayız, test data classında bu body oluşturduk.
        // bu response bodyyi buraya çagırıyoruz ve post edecegiz.
        HashMap<String, Object> requestBody=obje.setUpPostRequestBody();

        // Gönderdigim request dataları kontrol etmek için çagıracam ve response alacam.Aldıgım responseu kontrol edecm
        HashMap<String, Object> expectedData=obje.setUpPostExpectedData();


        Response response=given()
                .accept("application/json")
                .spec(spec03)
                .auth().basic("admin" , "admin321")  // Authorization yetkisini aşmak için...
                // Bu datalar şirket tarafından verilecektir.
                .body(requestBody) // Sadece body ekliyoruz ek olarak, get komutunuda post yapıyoruz.
                .when()
                .post("/{parametre1}/{parametre2}/{parametre3}");

        response.prettyPrint();

        // Authantication  --- kimlik erişim bilgileri. Benim ulaşmak için kullancagım ad ve şifre.
        // Authorization --- yetkilendirmedir. Hangi işlemleri yapacagımı belirler

        // Actual datamı oluşturdum
        HashMap<String, Object> actualData=response.as(HashMap.class);

        // Assertionları yapıyorum.
        Assert.assertEquals( expectedData.get("statuscode") , response.getStatusCode() );
        Assert.assertEquals( expectedData.get("status") , actualData.get("status") );
        Assert.assertEquals( expectedData.get("message") , actualData.get("message") );

        // ======================================= JsonPath İle ==================================================

        JsonPath json= response.jsonPath();

        Assert.assertEquals( expectedData.get("status") , json.getString( "status" ) );
        Assert.assertEquals( expectedData.get("message") , json.getString("message") );


        // ========================================================================================================

    }

}
