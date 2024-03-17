

@tag
Feature: Error Validation
  I want to use this template for my feature file


  @ErrorValidation
  Scenario Outline: Negative Test of logging in
    Given I landed on Commerce Page
    When Logged in with username <name> and password <password>
    Then "Incorrect email or password." message is displayed

    Examples: 
      | name  									| password 		| 
      | heba.sahib14@gmail.com 	| HebaShahib1 | 
