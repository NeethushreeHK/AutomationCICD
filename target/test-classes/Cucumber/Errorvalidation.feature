@tag
Feature: Error validation 

@errovalidation
Scenario Outline: Error validation
Given I landed on Ecommerce page
When  Logged in with username <name> and password <password>
Then "Incorrect email or password." message is displayed.
Examples:
|name                    | password   | 
|neethushreehk@gmail.com | Neethu5690 |