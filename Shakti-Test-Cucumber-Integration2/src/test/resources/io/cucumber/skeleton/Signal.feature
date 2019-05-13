@Signal
Feature: Signal Feature
  Test the Signal Services

  @API
  Scenario: Test Signal Services API
    Given I have below Details for Signal   	
    	|description | measurementType | name     |number|parentId                            |sourceUnit|systemHigh|systemLow|type   |
    	|automation  |    	           | signal123|108OFF3 |79B7A0E5-5D53-4550-ADB7-F9E7BCE557D2|     |0      |0      |Analog|
    When I post get getAll put get delete get getall Signal
    Then validate the Signal results 