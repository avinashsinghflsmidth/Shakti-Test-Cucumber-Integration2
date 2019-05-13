@HealthCheck
Feature: Other Relation Feature
  Test the Relation Services

  @API
  Scenario: Test Relation Services API
    Given I have Details for HealthCheck
   	When I getAll HealthCheck
    Then validate the HealthCheck results 
