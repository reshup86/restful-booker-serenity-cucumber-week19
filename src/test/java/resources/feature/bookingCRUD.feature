Feature: Restful Booker app

  As a user I want to test the Restful booker api

  Scenario Outline: Restful booker CRUD test
    Given I am on the restful booker page
    When  I create auth code with username and password
    Then  I create a new booking by providing the information firstname "<firstname>" lastname "<lastname>" totalprice "<totalprice>" depositpaid "<depositpaid>" booking dates additionalneeds "<additionalneeds>"
    And   I verify the booking with firstname "<firstname>" is created
    And   I update the booking with information firstname "<firstname>" lastname "<lastname>" totalprice "<totalprice>" depositpaid "<depositpaid>" booking dates additionalneeds "<additionalneeds>"
    And   I verify that the booking with the firstname "<firstname>" has been updated successfully
    And   I delete the booking created with firstname "<firstname>"
    Then  I verify that the booking is deleted successfully from the database
    Examples:
      | firstname | lastname   | totalprice | depositpaid | additionalneeds |
      | Kalie     | Doddwell   | 799        | Yes         | Brunch          |
      | Sushan    | Croper     | 299        | No          | Tea             |
      | Brew      | Risso      | 199        | No          | Breakfast       |
