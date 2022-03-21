package testData;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class JsonPlaceHolderTestData {

    // Bu classa obje ile ulaşıyoruz.

    public Map<String,Object> setUpTestData () {
        // Expected Data oluşturdum.
        HashMap<String, Object> expectedData = new HashMap<String, Object>();
        expectedData.put("statusCode", 200);
        expectedData.put("Via", "1.1 vegur");
        expectedData.put("Server", "cloudflare");
        expectedData.put("userId", 1);
        expectedData.put("title", "quis ut nam facilis et officia qui");
        expectedData.put("completed", false);
        System.out.println("expectedData Map Listesi= " + expectedData);
        return expectedData;
    }






    public JSONObject setUpTestAndRequestData() {

        JSONObject expectedRequest=new JSONObject();

        expectedRequest.put("statuscode", 201);
        expectedRequest.put("userId", 55);
        expectedRequest.put("title", "Tidy your room");
        expectedRequest.put("completed", false);


        return expectedRequest;
    }





    public JSONObject setUpPutData() {

        JSONObject expectedRequest = new JSONObject();
        expectedRequest.put("userId" , 333);
        expectedRequest.put("title" , "Come Back Here!");
        expectedRequest.put("completed" , false);

        return expectedRequest;

    }












    }
