Feature: Marking existing TODOs as Completed

  As a user, I would like to completed a TODO List

  Scenario: Completing one TODO item
    Given user opens the TODO List
    When user adds TODOs
      | Buy The Groceries |
      | Go for a run      |
    And then wait for 1 seconds
    Then Complete the following TODOs
      | Buy The Groceries |
    And then wait for 1 seconds
    Then Verify if the Completed tasks has
      | Buy The Groceries |
    And Verify if the number of Task list has "1" items

  Scenario: Completing one TODO item and then marking it as not complete
    Given user opens the TODO List
    When user adds TODOs
      | Buy The Groceries |
      | Go for a run      |
    And then wait for 1 seconds
    Then Complete the following TODOs
      | Buy The Groceries |
    And then wait for 1 seconds
    Then Verify if the Completed tasks has
      | Buy The Groceries |
    And Verify if the number of Task list has "1" items
    Then mark UnComplete the following TODOs
      | Buy The Groceries |
    And then wait for 1 seconds
    Then Verify if the All tasks have the following TODO
      | Buy The Groceries |
      | Go for a run      |
    And then wait for 1 seconds
    And Verify if the number of Task list has "2" items


  Scenario: Completing All TODO items in the List
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
    Then Verify if the Completed tasks has
      | Buy The Groceries  |
      | Go for a walk      |
      | Do the Dishes      |
      | Finish the laundry |
      | Water the plants   |
    And Verify if the number of Task list has "0" items


  Scenario: Completing one TODO item and verifying the Active TODO List
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
    Then Verify if the Active tasks does not have the following TODO
      | Buy The Groceries |
      | Go for a walk     |
    And then wait for 1 seconds
    Then Verify if the Active tasks have the following TODO
      | Do the Dishes      |
      | Finish the laundry |
      | Water the plants   |
    And Verify if the number of Task list has "3" items


  Scenario: Completing one TODO item and verifying the All TODO List
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
    Then Verify if the Active tasks does not have the following TODO
      | Buy The Groceries |
      | Go for a walk     |
    And then wait for 1 seconds
    Then Verify if the Active tasks have the following TODO
      | Do the Dishes      |
      | Finish the laundry |
      | Water the plants   |
    Then Verify if the All tasks have the following TODO
      | Buy The Groceries  |
      | Go for a walk      |
      | Do the Dishes      |
      | Finish the laundry |
      | Water the plants   |
    And Verify if the number of Task list has "3" items
