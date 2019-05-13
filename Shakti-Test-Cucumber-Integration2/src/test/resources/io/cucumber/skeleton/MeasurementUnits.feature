@MeasurementUnit
Feature: Measurement Unit Feature
  Test the Measurement Unit workflow - Post, Get

  @API
  Scenario: Test Measurement Unit API
    Given I have below details
    |alias|measurementUnit|
    |test2|ACRE|  
    When I post then get
    Then validate the Measurement results 
