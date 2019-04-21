# TODO I don't get it. Where is the steps from home task ? You should implement it as is, without modifications.
Feature: Home Page and User Table Page on Cucumber

  # TODO Same story with parametrisation
  Scenario: Checking Home Page And User Table Page
    Given I am on the Home Page
    Then The browser title is Home Page
    When I login as user PITER_CHAILOVSKII
    Then The PITER_CHAILOVSKII user name is displayed
    And I open User Table Page through the header menu Service -> USER_TABLE
    And I check Number and User columns of Users table
    Then User table contain correct values for numbers and users
      | 1 | Roman            |
      | 2 | Sergey Ivan      |
      | 3 | Vladzimir        |
      | 4 | Helen Bennett    |
      | 5 | Yoshi Tannamuri  |
      | 6 | Giovanni Rovelli |
    When I check Description column of Users table
    Then All cells of 'Description' column contains text
      | 1 | Lorem ipsum |
      | 2 | Lorem ipsum |
      | 3 | Lorem ipsum |
      | 4 | Lorem ipsum some description|
      | 5 | Lorem ipsum some description|
      | 6 | Lorem ipsum some description|
    When I set 'vip' status to Sergey Ivan
    Then 'Log' section shows a 1 log row in format: Vip: condition changed to true
    When I click on dropdown in column Type for user Roman
    Then Droplist contains values
      | Admin |
      | User |
      | Manager |