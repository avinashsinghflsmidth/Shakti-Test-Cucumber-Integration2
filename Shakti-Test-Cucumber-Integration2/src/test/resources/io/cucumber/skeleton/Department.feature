@Department
Feature: Department Services Feature
  Test the Department Services

  @API
  Scenario: Test Department Services API
    Given I have below Details for Department   	
    	|description | name | number |parentId |
    	|automation  | auto	| 123    |26FCF39C-BCAA-4E04-BCE7-2859033AB959|
    When I post get getAll put get delete get getall
    Then validate the department results 
    
