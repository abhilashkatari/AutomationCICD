
@tag
Feature: Error validation
  I want to use this template for my feature file

  @ErrorValidation
  Scenario Outline: Title of your scenario outline
    Given I landed on the Ecommerce Page
    When Logged in with the username <name> and password <password>
    Then "Incorrect email or password." message is displayed

    Examples: 
      | name  					| password 	   |
      | abhi@access.com | abac3$ 			 |
