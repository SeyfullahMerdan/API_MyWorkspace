package requests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest01 {

    // API'de Gherkin language anatotionları kullanıyoruz. Given, When, Then, And sözcükleri Gherkinden
    // gelen ifadelerdir.

    // given => önce gereklilikleri bildiriyorum. Dili vs. belirtmiş oluyorum.   accept gibi
    // when => kullanıcı olarak istedigimi belirtecem..  get,post,delete,put
    // then => çıktıları ifade ediyor. Assert işlemleri olduğu zaman genelde bu ifadeyi kullanırım
    // and => çoklu işlem olursa kullanılır.
 // Gherkin dili ile ilgili sadece bu kısmı kullanıyoruz.

    @Test
    public void request01() {

        // API testi yaparken uygulamamız gereken adımlar:
        // 1-) ilk olarak URL (endpoint) belirlenmelidir.
        // 2-) Daha sonra beklenen sonuç (expected result) oluşturulur.
        //  --- Body dogrulaması istendiginde expected result oluşturuyoruz
        // 3-) Request gönderiyoruz.
        // 4-) Actual Result oluşturuyoruz
        // 5-) Dogrulama yapıyoruz.


        String url="https://restful-booker.herokuapp.com/booking/3";  //endpoint oluşturdum.

        Response response=given().accept("application/json").when().get(url); // request gönderdim.
        //Bu request bana bir sonuç Response döndürecek, bu şekilde bırktıgımda sadece sonuç döner,
        // bunu bir yerde saklamalıyım. Bu yüzden de Response classından bir objeye assign ediyorum.

        response.prettyPrint();

        // Dogrulama yapmak için yapıyoruz.

        // İstedigim veriler Header kısımında oldugu için bu şekilde yazdırıp gördüm.
        System.out.println("Status codu: " +response.getStatusCode());
        System.out.println("Content type: " +response.getContentType());
        System.out.println("Status line: " + response.getStatusLine());

        // Expected kısmı bize verilen deger, actual kısmı ise responsedan dönen degerdir.
        Assert.assertEquals(200, response.getStatusCode()); // responsedan gelen status codu verir
        // status cod her zaman int olarak döner.
        Assert.assertEquals("application/json; charset=utf-8", response.getContentType());
        Assert.assertEquals("HTTP/1.1 200 OK", response.getStatusLine()); // responsedan gelen status codu verir


        // Komple Header da bulunan elementleri yazdırabilirim.

        System.out.println("response Tüm Header"+response.getHeaders());

        // Ben normal Assert yapabilirim veya Responsedan gelen Asserti kullanabilirim.
        // Burada iki türlü Assert yapabilirim, Hard Assertion ile veya Responsedan gelen
        // Gherkin dilindeki assert ile, yani then ile başlayan .assertThat methodları sıralaması ile

        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                statusLine("HTTP/1.1 200 OK");








    }



}
