package testData;

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

}
