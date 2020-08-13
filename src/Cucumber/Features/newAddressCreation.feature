Feature: Add new address on Your account


  Scenario Outline: Add new address on Your account after login
    Given User is logged in to MyStore shop
    When User goes to YourAccountPage
    And User goes to Addresses
    And User creates new address "<Company>", "<Address>", "<City>", "<Postal Code>", "<Phone>"
    Then User checks data of new created address "<Company>", "<Address>", "<City>", "<Postal Code>", "<Phone>"
    Examples:
      | Company         | Address       | City           | Postal Code   | Phone         |
      | Comp            | Address       | City           | Code          | 123456789     |
      | Comp1           | Address1      | City1          | Code1         | 987654321     |
      | Comp2           | Address2      | City2          | Code2         | 234567899     |
