
@tag
Feature: Purchase the order from Ecommerce website
  I want to use this template for my feature file

Background:
Given I landed on Commerce Page



  @tag2
  Scenario Outline: Positive Test of submitting the order
    Given Logged in with username <name> and password <password>
    When I add product <productname> to cart
    And checkout <productname> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

    Examples: 
      | name  									| password 				| productname |
      | heba.shahib14@gmail.com | HebaShahib1 		|	ZARA COAT 3 |
