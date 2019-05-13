@OtherAssets
Feature: Other Assets Services Feature
  Test the Other Assets Services

  @API
  Scenario: Test Other Assets Services API
    Given I have below Details for Other Assets   	
    	|description | name | parentId |
    	|automation  | auto	| 26FCF39C-BCAA-4E04-BCE7-2859033AB959|
    When I post get getAll put get delete get getall Other Assets
    Then validate the Other Assets results 