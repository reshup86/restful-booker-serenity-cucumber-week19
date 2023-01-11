package com.restful.booker.cucumber.steps;

import com.restful.booker.info.UserSteps;
import com.restful.booker.utils.TestUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

public class MyStepdefs_booking {
    static int bookingId;
    static int bookingId1;
    static String token;
    static String firstname = null;
    static String username = "admin";
    static String password = "password123";

    @Steps
    UserSteps userSteps;
    static ValidatableResponse response;

    @Given("^I am on the restful booker page$")
    public void iAmOnTheRestfulBookerPage() {
    }

    @When("^I create auth code with username and password$")
    public void iCreateAuthCodeWithUsernameAndPassword() {
        response = userSteps.authCode(username,password).statusCode(200);
        token = response.log().all().extract().path("token");
    }



    @Then("^I create a new booking by providing the information firstname \"([^\"]*)\" lastname \"([^\"]*)\" totalprice \"([^\"]*)\" depositpaid \"([^\"]*)\" booking dates additionalneeds \"([^\"]*)\"$")
    public void iCreateANewBookingByProvidingTheInformationFirstnameLastnameTotalpriceDepositpaidBookingDatesAdditionalneeds(String firstname1, String lastname, int totalprice, boolean depositpaid, String additionalneeds){
        HashMap<String,String> bookingdates = new HashMap<>();
        bookingdates.put("checkin", "2022-12-01");
        bookingdates.put("checkout", "2022-12-15");
        firstname = TestUtils.getRandomValue() + firstname1;

        response = userSteps.createBooking(firstname, lastname, totalprice, depositpaid, bookingdates, additionalneeds);
        bookingId = response.log().all().extract().path("bookingid");
    }

    @And("^I verify the booking with firstname \"([^\"]*)\" is created$")
    public void iVerifyTheBookingWithFirstnameIsCreated(String firstname1) {
        response.statusCode(200);
        HashMap<String, Object> bookingMap = userSteps.findBookingById(bookingId);
        Assert.assertThat(bookingMap, hasValue(firstname));
    }


    @And("^I update the booking with information firstname \"([^\"]*)\" lastname \"([^\"]*)\" totalprice \"([^\"]*)\" depositpaid \"([^\"]*)\" booking dates additionalneeds \"([^\"]*)\"$")
    public void iUpdateTheBookingWithInformationFirstnameLastnameTotalpriceDepositpaidBookingDatesAdditionalneeds(String firstname1, String lastname, int totalprice, boolean depositpaid, String additionalneeds){
        firstname = firstname + "_updated";

        HashMap<String,String> bookingdates = new HashMap<>();
        bookingdates.put("checkin", "2022-12-01");
        bookingdates.put("checkout", "2022-12-15");
        response = userSteps.updateBookingById(firstname, lastname, totalprice, depositpaid, bookingdates, additionalneeds, bookingId, username, password);
    }

    @And("^I verify that the booking with the firstname \"([^\"]*)\" has been updated successfully$")
    public void iVerifyThatTheBookingWithTheFirstnameHasBeenUpdatedSuccessfully(String firstname1) {
        HashMap<String, Object> bookingMap = userSteps.findBookingById(bookingId);
        Assert.assertThat(bookingMap, hasValue(firstname));
    }

    @And("^I delete the booking created with firstname \"([^\"]*)\"$")
    public void iDeleteTheBookingCreatedWithFirstname(String firstname1) {
        response = userSteps.deleteBookingById(bookingId, username, password);
    }

    @Then("^I verify that the booking is deleted successfully from the database$")
    public void iVerifyThatTheBookingIsDeletedSuccessfullyFromTheDatabase() {
        response.statusCode(201);
        userSteps.getBookingById(bookingId).statusCode(404);
    }


    //____________________________________________________________________________________________

    @When("^I send a POST request to auth endpoint to get authorization code$")
    public void iSendAPOSTRequestToAuthEndpointToGetAuthorizationCode() {
        response = userSteps.authCode(username, password);
    }

    @Then("^I must get back a valid status code \"([^\"]*)\"$")
    public void iMustGetBackAValidStatusCode(int code){
        response.statusCode(code);
    }

    @When("^I send a POST request to booking endpoint with the information firstname \"([^\"]*)\" lastname \"([^\"]*)\" totalprice \"([^\"]*)\" depositpaid \"([^\"]*)\" booking dates additionalneeds \"([^\"]*)\"$")
    public void iSendAPOSTRequestToBookingEndpointWithTheInformationFirstnameLastnameTotalpriceDepositpaidBookingDatesAdditionalneeds(String firstname, String lastname, int totalprice, boolean depositpaid, String additionalneeds){
        HashMap<String,String> bookingdates = new HashMap<>();
        bookingdates.put("checkin", "2022-12-01");
        bookingdates.put("checkout", "2022-12-15");

        response = userSteps.createBooking(firstname,lastname,totalprice,depositpaid,bookingdates,additionalneeds).statusCode(200);
        bookingId1 = response.log().all().extract().path("bookingid");
        System.out.println(bookingId);
    }

    @When("^I send a GET request to bookingId endpoint$")
    public void iSendAGETRequestToBookingIdEndpoint() {
        response = userSteps.getBookingById(bookingId1);
    }

    @When("^I send a PUT request to bookingId endpoint with updated information firstname \"([^\"]*)\" lastname \"([^\"]*)\" totalprice \"([^\"]*)\" depositpaid \"([^\"]*)\" booking dates additionalneeds \"([^\"]*)\"$")
    public void iSendAPUTRequestToBookingIdEndpointWithUpdatedInformationFirstnameLastnameTotalpriceDepositpaidBookingDatesAdditionalneeds(String firstname, String lastname, int totalprice, boolean depositpaid, String additionalneeds){
        HashMap<String,String> bookingdates = new HashMap<>();
        bookingdates.put("checkin", "2022-12-01");
        bookingdates.put("checkout", "2022-12-15");

        response = userSteps.updateBookingById(firstname, lastname,totalprice,depositpaid,bookingdates,additionalneeds, bookingId1,username,password);
    }

    @When("^I send a DELETE request to bookingId endpoint$")
    public void iSendADELETERequestToBookingIdEndpoint() {
        response = userSteps.deleteBookingById(bookingId1, username,password);
    }

}
