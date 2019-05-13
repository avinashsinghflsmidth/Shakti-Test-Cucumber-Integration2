@Relation
Feature: Other Relation Feature
  Test the Relation Services

  @API
  Scenario: Test Relation Services API
    Given I have Details for Relation 26FCF39C-BCAA-4E04-BCE7-2859033AB959
   	When I getAll Relation
    Then validate the Relation results 
