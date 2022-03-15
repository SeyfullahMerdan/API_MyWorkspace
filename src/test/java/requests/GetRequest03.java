package requests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest03 {

    @Test
    public void test03(){

        // url oluşturdum
        String url="https://restful-booker.herokuapp.com/booking/1001";

        // request gönderdim ve gelen response objeme assign ettim, kullacam.
        Response response=given().accept(ContentType.JSON).when().get(url);

        // Expected data oluşturmuyorum çünkü hata verecek, bekledigim bir data yok.

        // response aserttini kullanıyorum
        response.then().assertThat().statusCode(404);

        // response json türünde oldugu için javadaki String methodlarını hemen kullanamam.
        // response ile java classındaki String methodu kullanmam için -contains- gibi..
        // önce response'u String haline getirmesi için asString methodundan faydalanırım

        Assert.assertTrue(response.asString().contains("Not Found"));
        Assert.assertFalse(response.asString().contains("API"));

        // asString methodu ile Json formatında gelen response'u Stringe çevirdik.


    }

}
