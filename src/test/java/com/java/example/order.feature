Feature: getOrders
  Background:
    * configure report = true
    * def variables =
    """
    {
      "userId": 1
    }
    """

  Scenario: getOrders
    * def result = call read('call_graphql_with_query.feature') { queryName: 'getOrders', variables: #(variables) }
    * print result
    And match result..content contains deep [{ id: #present }]