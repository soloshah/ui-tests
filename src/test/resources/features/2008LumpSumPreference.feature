@2008lumpSumPreference @BP490-147
Feature: 2008 Lump sum preference

Background:
Given I am on the start page
When I go to the 2008 lumpsum preference page


Scenario: Select lumpsum preference
And I select valid 2008 lumpsum preference using MaxTaxFreeAmount
Then the valid 2008 lumpsum preference text for MaxTaxFreeAmount will be displayed
And the 2008 lumpsum preference submission will be successful
And the tbi page will be displayed


Scenario: Select lumpsum preference
And I select valid 2008 lumpsum preference using LessTaxFreeAmount
Then the valid 2008 lumpsum preference text for LessTaxFreeAmount will be displayed
And the amount field for 2008 lumpsum preference will be displayed
And I enter valid amount dividable by 12 for 2008 lumpsum preference
Then the tbi page will be displayed


Scenario: Select lumpsum preference
And I select valid 2008 lumpsum preference using MaxAdditionalLumpSum
Then the valid 2008 lumpsum preference text for MaxAdditionalLumpSum will be displayed
And the 2008 lumpsum preference submission will be successful
And the tbi page will be displayed


Scenario: 2008 Select lumpsum preference
And I dont select any 2008 lumpsum preference
Then the 2008 lumpsum preference submission will be unsuccessful
And the error message 'Select how you would like your lump sum' for 2008 lumpsum preference will be displayed


Scenario: Default value of 2008 lumpsum preference
Then the default value of 2008 lumpsum preference will be blank


@Bug @BP490-315
Scenario Outline: Less than max tax free amount validation
And I select valid 2008 lumpsum preference using LessTaxFreeAmount
Then the valid 2008 lumpsum preference text for LessTaxFreeAmount will be displayed
And the amount field for 2008 lumpsum preference will be displayed
And I enter invalid amount '<amount>' for 2008 lumpsum preference
And the amount error message '<errorMessage>' for 2008 lumpsum preference will be displayed
Examples:
|amount |errorMessage                                  |
|       |Enter your lump sum amount in multiples of 12 |
|152363 |Enter your lump sum amount in multiples of 12 |
|*&^%Ab |Enter your lump sum amount in multiples of 12 |
|12.00  |Enter your lump sum amount in multiples of 12 |
|00000  |Enter your lump sum amount in multiples of 12 |
|-1440  |Enter your lump sum amount in multiples of 12 |
         