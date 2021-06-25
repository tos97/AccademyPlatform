Feature: Exercise on web site Trenitalia

  Background: Open site and accept cookie
    Given open site
    And close banner

  Scenario: Open site and accept cookie
    Then banner closing control

  Scenario: Research
    Given departure: Ferrara and arrival: Pescara
    Then check if there are any results

  Scenario: Research2
    Given departure: Ferrara and arrival: Pescara
    Then check cart
