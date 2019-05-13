@Site
Feature: Site Services Feature
  Test the Site Services

  @API
  Scenario: Test Site Services API
    Given I have below Details for Site   	
    	|country |crmNumber  |description   |latitude |longitude|name   |
    	|In   |777	     | automation    |20       |70      |chennai|
    When I post get getAll put get delete get getall site
    Then validate the Site results 
