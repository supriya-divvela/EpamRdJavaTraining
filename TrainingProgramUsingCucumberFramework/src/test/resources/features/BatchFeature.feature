Feature: execute batch service

  Scenario: get batch by id
    Given batch 1
    When requested to find batch
    Then the status code should be 200

  Scenario Outline: post a batch
    Given i will give a request body for batch
    When the user retrieve the details with <batchId> and "<batchName>" and <menteeId>,"<menteeName>","<email>""
    Then the user verify the response code 201
    And the user verify the response body <batchId> and "<batchName>" and <menteeId>,"<menteeName>","<email>"

    Examples: 
      | batchId | batchName | menteeId | menteeName | email         |
      |       1 | Java      |        1 | supriya    | sup@gmail.com |

  Scenario: get all batches
    Given batch url
    When requested to find all batch
    Then the status code should for all batches 200

  Scenario: delete batch by id
    Given delete batch with id 1
    When requested to delete batch
    Then the status code for delete batch should be 204

  Scenario Outline: update a batch
    Given i will give a request body to update a batch
    When the user retrieve the details for update request with <batchId> and "<batchName>" and <menteeId>,"<menteeName>","<email>""
    Then the user verify the response code for update should be 200
    And the user verify the updated response body <batchId> and "<batchName>" and <menteeId>,"<menteeName>","<email>"

    Examples: 
      | batchId | batchName | menteeId | menteeName | email         |
      |       1 | Java      |        1 | supriya    | sup@gmail.com |
