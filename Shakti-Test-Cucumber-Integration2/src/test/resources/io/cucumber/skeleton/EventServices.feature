@EventServices
Feature: Event Services Feature
  Test the Event Services - Get

  @API
  Scenario: Test Event Services API
    Given I have the event details    	
    	|assetId | attachmentUrl | description |id |occurence |severity |timestamp|
    	|280302CC-5E1D-48A9-9CEC-8411A765B176 | testing only| string |1557489398137 |test|WARNING|1557489398137|
    When I post getall get events
    Then validate the results 