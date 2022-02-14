Feature: Creating new TODOs

  As a busy person, I would like to create and maintain a TODO List

  Scenario: Adding one TODO item
    Given user opens the TODO List
    When user adds TODOs
      | Buy The Groceries |
    And then wait for 1 seconds
    Then his TODO List should contain
      | Buy The Groceries |
    And Verify if the number of Task list has "1" items

  Scenario: Adding Multiple TODO item
    Given user opens the TODO List
    When user adds TODOs
      | Buy The Groceries |
      | Go for a run      |
    And then wait for 1 seconds
    Then his TODO List should contain
      | Buy The Groceries |
      | Go for a run      |
    And Verify if the number of Task list has "2" items
