Feature: Home Page and Different Elements Page on Cucumber

  Scenario: Checking Home Page and Different Elements Page
    Given I am on the Home Page
    Then The browser title is Home Page
    When I login as user PITER_CHAILOVSKII
    Then The PITER_CHAILOVSKII user name is displayed
    And Home Page contains benefits
    And Home Page contains texts above benefits
    And Home Page navigation Service dropdown contains options
    And Home Page side bar dropdown contains options
    Then I'm on DIFFERENT_ELEMENTS page
    Then Different Elements page contains checkboxes, radiobuttons, dropdown menu, buttons, left and right sections
    Then Different Elements page contains right Section
    Then Different Elements page contains left Section
    When Checkbox is clicked
      | Water |
      | Wind  |
    Then I check log is true for the ELEMENTS
    Then Radiobutton SELEN is clicked
    Then I check log for radiobutton SELEN
    When YELLOW color is selected from the dropdown list
    Then I check log for dropdown color YELLOW
    When Checkbox is clicked
      | Water |
      | Wind  |
    Then I check log is false for the ELEMENTS




