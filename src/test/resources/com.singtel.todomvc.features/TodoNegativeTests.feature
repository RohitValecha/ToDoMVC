Feature: Negative TODOs Tests

  As a busy person, I would like to create and maintain a TODO List

  Scenario: Adding empty TODO item
    Given user opens the TODO List
    When user adds TODOs
      |  |
    And then wait for 1 seconds
    Then Verify if the task list does not have following tasks
      |  |


  Scenario: Adding special characters TODO item
    Given user opens the TODO List
    When user adds TODOs
      | !@#$@%^&*(*)(_+_{} |
    And then wait for 1 seconds
    Then his TODO List should contain
      | !@#$@%^&*(*)(_+_{} |
    And Verify if the number of Task list has "1" items
