Feature: Deleting existing TODOs

  As a user, I would like to delete a TODO List

  Scenario: Delete one TODO item
    Given user opens the TODO List
    When user adds TODOs
      | Go for a walk |
    Then his TODO List should contain
      | Go for a walk |
    And then wait for 1 seconds
    When user wants to delete TODO
      | Go for a walk |
    And then wait for 1 seconds
    Then Verify if the task list does not have following tasks
      | Go for a walk |


  Scenario: Delete multiple TODO items
    Given user opens the TODO List
    When user adds TODOs
      | Buy The Groceries  |
      | Go for a walk      |
      | Do the Dishes      |
      | Finish the laundry |
      | Water the plants   |
    Then his TODO List should contain
      | Buy The Groceries  |
      | Go for a walk      |
      | Do the Dishes      |
      | Finish the laundry |
      | Water the plants   |
    And then wait for 1 seconds
    When user wants to delete TODO
      | Buy The Groceries |
      | Go for a walk     |
      | Do the Dishes     |
    And then wait for 1 seconds
    Then Verify if the task list does not have following tasks
      | Buy The Groceries |
      | Go for a walk     |
      | Do the Dishes     |
    And Verify if the number of Task list has "2" items
