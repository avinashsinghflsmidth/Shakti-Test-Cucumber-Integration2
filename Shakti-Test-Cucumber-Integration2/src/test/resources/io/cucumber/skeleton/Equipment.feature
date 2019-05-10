@Equipment
Feature: Equipment Services Feature
Test the Equipment Services
@API
Scenario: Test Equipment Services API
Given I have below Details for Equipment

|description | name | number | parentId | subType | type |
|automation | auto | 123 |26FCF39C-BCAA-4E04-BCE7-2859033AB959|subtype|type|
When I post get getAll put get delete get getAll equipment
Then validate the equipment results
    
