package baseUrl;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class DummyBaseUrl {

    protected RequestSpecification spec03;

    @Before
    public void setUp(){

        String uri="http://dummy.restapiexample.com";
        spec03=new RequestSpecBuilder().setBaseUri(uri).build();

    }


}
