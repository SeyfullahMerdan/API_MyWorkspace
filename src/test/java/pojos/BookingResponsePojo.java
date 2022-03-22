package pojos;

public class BookingResponsePojo {


    private int bookingid;
    private BookingPojos booking;



    public int getBookingid() {
        return bookingid;
    }

    public void setBookingid(int bookingid) {
        this.bookingid = bookingid;
    }

    public BookingPojos getBooking() {
        return booking;
    }

    public void setBooking(BookingPojos booking) {
        this.booking = booking;
    }



    public BookingResponsePojo() {
    }

    public BookingResponsePojo(int bookingid, BookingPojos booking) {
        this.bookingid = bookingid;
        this.booking = booking;
    }



    @Override
    public String toString() {
        return "BookingResponsePojo{" +
                "bookingid=" + bookingid +
                ", booking=" + booking +
                '}';
    }


}
