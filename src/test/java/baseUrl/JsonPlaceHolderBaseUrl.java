package baseUrl;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class JsonPlaceHolderBaseUrl {

    protected RequestSpecification spec01;
    // İnterfacedir. Daha sonra kullanmak için buraya Url tanımlaması yapacam. Child classlar ulaşsın diye
    // protected yaptım.

    @Before  // Testi çalıştırmadan önce bunu çalıştırsın istiyorum. Extends yapan her classtan önce çalışacak.
    public void setUp(){

        spec01=new RequestSpecBuilder().
                setBaseUri("http://jsonplaceholder.typicode.com").
                build();


        // Bu URL'i al spec yapısı kur ve her testten önce çalıştır dedim.


    }


}
