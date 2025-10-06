Feature: Login to the banking app

  As a user of the banking app
  I want to be able to log in securely
  So that I can access my account information and perform transactions

  Scenario: Successful login with valid credentials
    Given I am on the login page
    When I enter my username as "testuser"
    And I enter my password as "testpassword"
    And I click the login button
    Then I should see the dashboard page
    And I should see my account balance

  Scenario: Unsuccessful login with invalid credentials
    Given I am on the login page
    When I enter my username as "invaliduser"
    And I enter my password as "invalidpassword"
    And I click the login button
    Then I should see an error message "Invalid username or password"
    And I should still be on the login page

  Scenario: Login with empty username
    Given I am on the login page
    When I enter my username as ""
    And I enter my password as "testpassword"
    And I click the login button
    Then I should see an error message "Username cannot be empty"
    And I should still be on the login page

  Scenario: Login with empty password
    Given I am on the login page
    When I enter my username as "testuser"
    And I enter my password as ""
    And I click the login button
    Then I should see an error message "Password cannot be empty"
    And I should still be on the login page