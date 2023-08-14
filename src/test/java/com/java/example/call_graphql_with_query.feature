Feature: GraphQL API

  Scenario:
    * configure headers = {'Accept': 'application/json', 'Content-Type': 'application/json'}
    * def query = read('this:' + queryName + '.graphql')
    Given url baseUrl + '/graphql'
    And request { query: '#(query)', variables: '#(variables)' }
    When method post
    Then status 200
    Then match response.errors == '#notpresent'