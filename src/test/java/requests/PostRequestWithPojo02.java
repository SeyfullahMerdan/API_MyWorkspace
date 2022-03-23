package requests;

import baseUrl.HerokuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojos;
import pojos.BookingResponsePojo;

import static io.restassured.RestAssured.given;

public class PostRequestWithPojo02 extends HerokuAppBaseUrl {



    @Test
    public void test02() {

        spec02.pathParams("parametre1" , "booking");

        BookingDatesPojo bookingdates=new BookingDatesPojo("2020-09-09" ,"2020-09-21");
        BookingPojos bookingpojo=new BookingPojos("Selim" , "Ak" , 15000, true, bookingdates);
        System.out.println("bookingpojo = " + bookingpojo);

        Response response=given().contentType(ContentType.JSON)
                .spec(spec02)
                .auth().basic("admin" , "admin321")
                .body(bookingpojo)
                .post("/{parametre1}");
        response.prettyPrint();
        // Burada verileri gönderdim. Bu kısım sadece booking kısmının içiydi.
        // Geri gelen sonuçta bookingid ve booking şeklinde iki map türü dönecek...


        BookingResponsePojo actualData=response.as(BookingResponsePojo.class);


        Assert.assertEquals(200 , response.getStatusCode());
        Assert.assertEquals( bookingpojo.getFirstname() , actualData.getBooking().getFirstname()  );
        Assert.assertEquals( bookingpojo.getLastname() , actualData.getBooking().getLastname()  );
        Assert.assertEquals( bookingpojo.getTotalprice() , actualData.getBooking().getTotalprice()  );
        Assert.assertEquals( bookingpojo.isDepositpaid() , actualData.getBooking().isDepositpaid()  );
        Assert.assertEquals( bookingpojo.getBookingdates().getCheckin(),
                             actualData.getBooking().getBookingdates().getCheckin()
        );
        Assert.assertEquals (bookingpojo.getBookingdates().getCheckout(),
                             actualData.getBooking().getBookingdates().getCheckout()
        );






















    }


}
