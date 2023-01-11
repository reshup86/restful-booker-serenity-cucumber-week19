package com.restful.booker.testsuite;

import com.restful.booker.contants.EndPoints;
import com.restful.booker.testbase.TestBase;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class BookingTagsTest extends TestBase {
    @WithTags({
            @WithTag("bookingfeature:SMOKE"),
            @WithTag("bookingfeature:REGRESSION"),
    })
    @Title("Provide a 415 status code when incorrect HTTP method is used to access resource")
    @Test
    public void invalidMethod() {
        SerenityRest.rest()
                .given()
                .when()
                .post(EndPoints.CREATE_BOOKING)
                .then()
                .statusCode(415)
                .log().all();
    }

    @WithTags({
            @WithTag("bookingfeature:SANITY"),
            @WithTag("bookingfeature:REGRESSION"),
    })
    @Title("This test will verify if a status code of 200 is returned for GET request")
    @Test
    public void verifyIfTheStatusCodeIs200() {
        SerenityRest.rest()
                .given()
                .when()
                .get(EndPoints.GET_ALL_BOOKINGS)
                .then()
                .statusCode(200)
                .log().all();
    }

    @WithTag("bookingfeature:REGRESSION")
    @Title("This test will provide an error code of 404 when user tries to access an invalid resource")
    @Test
    public void inCorrectResource() {
        SerenityRest.rest()
                .given()
                .when()
                .get("/booking1")
                .then()
                .statusCode(404)
                .log().all();
    }
}
