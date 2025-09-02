@tag
Feature: Purchase the order from Ecommerce website 
Background:
Given  I landed on Ecommerce page

@Regression
Scenario Outline: Positive test of submitting order
Given Logged in with username <name> and password <password>
When  Add the product <prodname>
And Checkout <prodname> and submit order
Then "THANKYOU FOR THE ORDER." message is displayed on confirmpage.
Examples:
|name                    | password  | prodname    |
|neethushreehk@gmail.com | Neethu569 | ZARA COAT 3 |