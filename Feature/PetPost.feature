@Pet
Feature: feature to create Pet swagger POST request
  Scenario: Submit the POST request
    Given build the POST request payload with given details
    |id|name  |status   |
    |1 |doggie|available|
    When add the category "category"
    |id|name  |
    |1 |string|
    Then add photo urls
    |urls|
    | string|
    Then add tags
      |id|name  |
      |1 |string|
#      |2 |string|
#      |3 |string|
    Then generate the payload
    #Then validate the schema of the payload
    Then trigger the POST request
