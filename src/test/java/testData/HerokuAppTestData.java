package testData;

import org.json.JSONObject;

import java.util.HashMap;

public class HerokuAppTestData {


    public HashMap<String, Object> setUpTestData() {

        HashMap<String, Object> bookingDates=new HashMap<>();
        bookingDates.put("checkin" ,"2018-08-30");
        bookingDates.put("checkout" ,"2020-05-27");

        HashMap<String, Object> expectedData=new HashMap<>();
        expectedData.put("firstname" , "Mark");
        expectedData.put("lastname" , "Wilson");
        expectedData.put("totalprice" , 659);
        expectedData.put("depositpaid" , true);
        expectedData.put("bookingdates" , bookingDates);


        return expectedData;
    }




    public JSONObject setUpTestAndRequestData() {


        JSONObject bookingDates=new JSONObject();  // Data türünü kendisi belirliyor.!!!
        bookingDates.put("checkin", "2022-01-10");
        bookingDates.put("checkout", "2022-02-11");


        JSONObject expectedRequest=new JSONObject();
        expectedRequest.put("firstname", "seyfi");
        expectedRequest.put("lastname", "baba");
        expectedRequest.put("totalprice", 654);
        expectedRequest.put("depositpaid", true);
        expectedRequest.put("bookingdates", bookingDates);

        return expectedRequest;

    }

























}
