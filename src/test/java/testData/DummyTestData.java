package testData;

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









}
