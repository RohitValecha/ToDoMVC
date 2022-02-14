Feature: Clearing existing completed TODOs

  As a user, I would like to clear a completed TODO List

  Scenario: Clear Few Completed TODO items in the List and Verify if the Active tasks still exist
    Given user opens the TODO List
    When user adds TODOs
      | Buy The Groceries  |
      | Go for a walk      |
      | Do the Dishes      |
      | Finish the laundry |
      | Water the plants   |
    And then wait for 1 seconds
    Then Complete the following TODOs
      | Buy The Groceries |
      | Go for a walk     |
    And then wait for 1 seconds
    Then Clear all the completed tasks in the List
    And then wait for 1 seconds
    Then Verify if the task list does not have following tasks
      | Buy The Groceries |
      | Go for a walk     |
    Then his TODO List should contain
      | Do the Dishes      |
      | Finish the laundry |
      | Water the plants   |
    And Verify if the number of Task list has "3" items

  Scenario: Clear All Completed TODO items in the List
    Given user opens the TODO List
    When user adds TODOs
      | Buy The Groceries  |
      | Go for a walk      |
      | Do the Dishes      |
      | Finish the laundry |
      | Water the plants   |
    And then wait for 1 seconds
    Then Complete all the TODOs in the List
    And then wait for 1 seconds
    Then Clear all the completed tasks in the List
    And then wait for 1 seconds
    Then Verify if the task list does not have following tasks
      | Buy The Groceries  |
      | Go for a walk      |
      | Do the Dishes      |
      | Finish the laundry |
      | Water the plants   |