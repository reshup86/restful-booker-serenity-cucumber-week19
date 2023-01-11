package com.restful.booker.testsuite;

import com.restful.booker.info.UserSteps;
import com.restful.booker.testbase.TestBase;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.junit.annotations.Concurrent;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

@Concurrent(threads = "2x")
@UseTestDataFrom("src/test/java/resources/testdata/bookingsdata.csv")
@RunWith(SerenityParameterizedRunner.class)
public class BookingDataDrivenTest extends TestBase {
    private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;
    private String checkin;
    private String checkout;
    private String date1;
    private String date2;
    private String additionalneeds;

    @Steps
    UserSteps userSteps;


    @Title("Data driven Test to add multiple bookings to the api")
    @Test
    public void createMulitpleBookings(){
        HashMap<String,String> bookingdates = new HashMap<>();
        bookingdates.put(checkin, date1);
        bookingdates.put(checkout, date2);
        userSteps.createBooking(firstname,lastname,totalprice,depositpaid,bookingdates,additionalneeds);
    }
}
