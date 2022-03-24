package requests;

import baseUrl.DummyBaseUrl;
import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.Data;
import pojos.DummyPojo;

import static io.restassured.RestAssured.given;

public class GetRequestWithPojo01 extends DummyBaseUrl {

    /* {
    "status": "success",
    "data":
        {
            "id": 1,
            "employee_name": "Tiger Nixon",
            "employee_salary": 320800,
            "employee_age": 61,
            "profile_image": ""
        }
         "message": "Successfully! Record has been fetched."
} */


    @Test
    public void test () {

        spec03.pathParams("parametre1","api","parametre2","v1","parametre3","employee","parametre4","1");

        Data data=new Data(1, "Tiger Nixon" , 320800 , 61 , "" );
        DummyPojo expectedData=new DummyPojo( "success" , data , "Successfully! Record has been fetched." );


        Response response=given()
                .contentType(ContentType.JSON)
                .spec(spec03)
                .when()
                .get("/{parametre1}/{parametre2}/{parametre3}/{parametre4}");
        response.prettyPrint();


        DummyPojo actualData=response.as(DummyPojo.class);


        Assert.assertEquals( expectedData.getStatus() , actualData.getStatus() );
        Assert.assertEquals( expectedData.getData().getId() , actualData.getData().getId()  );
        Assert.assertEquals( expectedData.getData().getemployee_name() , actualData.getData().getemployee_name()  );
        Assert.assertEquals( expectedData.getData().getemployee_salary() , actualData.getData().getemployee_salary()  );
        Assert.assertEquals( expectedData.getData().getemployee_age() , actualData.getData().getemployee_age()  );
        Assert.assertEquals( expectedData.getData().getprofile_image() , actualData.getData().getprofile_image() );
        Assert.assertEquals( expectedData.getMessage() , actualData.getMessage() );

//=====================================================================================================================
        // Serilization Java yapısında olan dataları Json formatına dönüştürme işlemidir.

        // Gson sınıfından bir obje oluşturulur
        Gson gson = new Gson();
        String jsonFromJava=gson.toJson(actualData);
        System.out.println("json formatı = " + jsonFromJava);

        Gson gsonObjesi=new Gson();
        String javaDan=gsonObjesi.toJson(expectedData);
        System.out.println("javadan jsona = " + javaDan);

//=====================================================================================================================


    }


}
