package testData;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DummyTestData {


    public HashMap<String, Object> setUpTestData() {
        HashMap<String, Object> onbirinci=new HashMap<>();
        onbirinci.put("id" , 11);
        onbirinci.put("employee_name" , "Jena Gaines");
        onbirinci.put("employee_salary" , 90560);
        onbirinci.put("employee_age" , 30);
        onbirinci.put("profile_image" , "");
        List<Integer> yaslar=new ArrayList<>();
        yaslar.add(40);
        yaslar.add(21);
        yaslar.add(19);
        HashMap<String, Object> expectedData=new HashMap<>();
        expectedData.put("statuscode" , 200);
        expectedData.put("besincicalisaninismi" , "Airi Satou");
        expectedData.put("calisansayisi" , 24);
        expectedData.put("sondanikincicalisanmaas" , 106450);
        expectedData.put("onbirincicalisan" , onbirinci);
        expectedData.put("yas" , yaslar);
        return expectedData;
    }



    public HashMap<String, Object> setUpTestData15() {


        HashMap<String, Object> expectedData=new HashMap<>();

        expectedData.put("statuscode" , 200);
        expectedData.put("enyuksekmaas" , 725000);
        expectedData.put("enkucukyas" , 19);
        expectedData.put("ikincienyuksekmaas" , 675000);

        return expectedData;

    }



    public HashMap<String, Object> setUpPostRequestBody () {

        HashMap<String, Object> requestBody=new HashMap<>();
        requestBody.put("name" , "Seyfo");
        requestBody.put("salary" , "52000");
        requestBody.put("age" , "35" );
        requestBody.put("profile_image" , "yok");

        return requestBody;
    }



    public HashMap<String, Object> setUpPostExpectedData () {

        HashMap<String, Object> bodyData=new HashMap<>();
        bodyData.put("name" , "Seyfo");
        bodyData.put("salary" , "52000");
        bodyData.put("age" , "35");
        bodyData.put("profile_image" , "yok");

        HashMap<String, Object> requestBody=new HashMap<>();
        requestBody.put("statuscode" , 200);
        requestBody.put("status" , "success");
        requestBody.put("message" , "Successfully! Record has been added." );
        requestBody.put("data" , bodyData );

        return requestBody;
    }





    public JSONObject setUpDeleteExpectedData () {

        JSONObject expectedData=new JSONObject();
        expectedData.put("status" , "success");
        expectedData.put("data" , "2");
        expectedData.put("message" , "Successfully! Record has been deleted");

        return expectedData;


    }











}
