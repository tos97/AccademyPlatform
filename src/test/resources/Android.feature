Feature: Exercise on app android

  Background:
    Given open app

  @Mobile
  Scenario: Open app with error
    Given Login with username: francesco password: admin
    Then let's check if you log in with your credentials

  @Mobile
  Scenario: Open app with admin
    Given Login with username: admin password: admin
    Then let's check if you log in with your credentials

  @Mobile
  Scenario: Check login
    Then check if you are logged in with the right account

