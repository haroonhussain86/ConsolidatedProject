Feature: Lloyds bank reviews

  Scenario: Search for reviews
  Given : I find a review with 5/5 rating
    When : I search for a review with 5/5 review rating
    Then : Review ratings with 5/5 stars should be displayed
