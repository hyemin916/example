Feature: getOrders

  Background:
    * configure report = true
    * def variables =
    """
    {
      "userId": 1
    }
    """

  Scenario: get orders
    * def result = call read('call_graphql_with_query.feature') { queryName: 'getOrders', variables: #(variables) }
    * print result
    * match result..content contains deep [{ id: #present }]

  Scenario: get orders with products
    * def result = call read('call_graphql_with_query.feature') { queryName: 'getOrders', variables: #(variables) }
    * match result..products contains deep [{ name: #string }]

  Scenario: get orders with shipping
    * def result = call read('call_graphql_with_query.feature') { queryName: 'getOrders', variables: #(variables) }
    * match result..content contains deep [{ shipping: { id: #present } }]
