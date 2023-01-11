Feature: Testing different requests on the restful booker api

  As a user I want to test if data can be accessed, created,updated and deleted on the restful booker api

  Background: Restful booker api is running

  Scenario: Check if the auth code can be created on restful booker api
    When  I send a POST request to auth endpoint to get authorization code
    Then  I must get back a valid status code "200"

  Scenario: Check if a new booking can be created on the restful booker api
    When  I send a POST request to booking endpoint with the information firstname "Abbie" lastname "Crook" totalprice "999" depositpaid "Yes" booking dates additionalneeds "Dinner"
    Then  I must get back a valid status code "200"


  Scenario: Check if the restful booker api can be accessed by users by searching booking Id
    When  I send a GET request to bookingId endpoint
    Then  I must get back a valid status code "200"


  Scenario: Check if the restful booker api can be updated by users by searching booking Id
    When  I send a PUT request to bookingId endpoint with updated information firstname "Abbie" lastname "Crook" totalprice "999" depositpaid "Yes" booking dates additionalneeds "Dinner"
    Then  I must get back a valid status code "200"


  Scenario: Check if the users can delete the booking by searching booking Id
    When  I send a DELETE request to bookingId endpoint
    Then  I must get back a valid status code "201"