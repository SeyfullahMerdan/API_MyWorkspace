package requests.GetRequests;

import baseUrl.DummyBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static io.restassured.RestAssured.given;

public class GetRequest11 extends DummyBaseUrl {

    @Test
    public void test11() {

        spec03.pathParams("parametre1","api","parametre2","v1", "parametre3","employees");

        Response response=given()
                .accept(ContentType.JSON)
                .spec(spec03)
                .when()
                .get("/{parametre1}/{parametre2}/{parametre3}");

        response.prettyPrint();

        JsonPath json= response.jsonPath();

        Assert.assertEquals(200, response.getStatusCode());

        // Groovy dili javanın alt dilidir.
        // Biz bu dilin yardımı ile Loop kullanmadan gelen responsedaki degerleri
        // bir şarta baglı olarak listeye yazdırabilriz.
        // APIde şartlı sorgular yapabiliriz. Javanın bir alt dili gibidir.

        List<Integer>idList=json.getList( "data.findAll{it.id>10}.id" );
        // IDsi 10dan büyük olanların id'sini getir demektir.
        System.out.println("idList = " + idList);

        Assert.assertEquals(14, idList.size());


        List <Integer> yasList=json.getList("data.findAll{it.employee_age<30}.employee_age");
        // Yaşı 30dan küçük olanların employee_age'ini getir demektir.
        System.out.println("yasList = " + yasList);

        Collections.sort(yasList);  //Collectionlardan sort ile listeyi sıraladım
        // en son indexteki eleman en büyük elemandır

        Assert.assertEquals(23, (int)yasList.get(yasList.size()-1));
        // Casting işlemi istiyor,Cast yapmam gerekiyor.

        // Listimiz data type olarak Integer, assertEquals içine yazdıgımız degerlere baktıgımızda expected data int.
        // iken listsize-1 INTEGER classından. İkisini farkı data algılıyor ve bu yüzden cast yapmamızı istiyor
        // Ya Wrapper Class int.'a yada int. Wrapper Classa çevrilmeli.

        List <String> maasList=json.get("data.findAll{it.employee_salary>35000}.employee_name");
        // salarysi 35binden byk olanların isimlerini al dedim.
        System.out.println("maasList = " + maasList);
        Assert.assertTrue(maasList.contains("Charde Marshall"));


        List <Integer> maasList2=json.get("data.findAll{it.employee_salary>300000}.id");
        System.out.println("maasList 33bin altında olanlar:  = " + maasList2);



    }

}
