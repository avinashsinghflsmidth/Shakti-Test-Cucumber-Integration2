@Line
Feature: Other Line Feature
  Test the Line Services

  @API
  Scenario: Test Line Services API
    Given I have below Details for Line   	
    	|description | name | parentId |
    	|automation  | auto	| 26FCF39C-BCAA-4E04-BCE7-2859033AB959|
    When I post get getAll put get delete get getall Line
    Then validate the Line results