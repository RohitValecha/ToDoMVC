Feature: Editing existing TODOs

  As a busy person, I would like to edit and maintain a TODO List

  Scenario: Editing TODO item

    Given user opens the TODO List
    When user adds TODOs
      | Go for a walk |
    Then his TODO List should contain
      | Go for a walk |
    And then wait for 1 seconds
    When user wants to edit "Go for a walk" and change
      | Buy Groceries |
    And then wait for 1 seconds
    Then his TODO List should contain
      | Buy Groceries |
    And Verify if the number of Task list has "1" items


  Scenario: Multiple Editing TODO item

    Given user opens the TODO List
    When user adds TODOs
      | Do the Dishes      |
      | Finish the laundry |
      | Water the plants   |
    Then his TODO List should contain
      | Do the Dishes      |
      | Finish the laundry |
      | Water the plants   |
    And then wait for 1 seconds
    When user wants to edit "Do the Dishes" and change
      | Buy Groceries |
    When user wants to edit "Finish the laundry" and change
      | Go for a walk |
    And then wait for 1 seconds
    Then his TODO List should contain
      | Buy Groceries    |
      | Go for a walk    |
      | Water the plants |
    And Verify if the number of Task list has "3" items
