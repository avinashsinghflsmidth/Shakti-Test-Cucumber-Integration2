@SignalSubscription
Feature: Signal Subscription Feature
  Test the signal subscription workflow - Post, Get, Delete and Get

  @API
  Scenario: Test Signal Subscription API
    Given I have callbackUrl : automation and parentId : 46D06931-1106-4366-82B8-4B037782ECF6  
    When I post then get then delete then get
    Then posted subscripion will be in get result deleted subscription will not be in get result
    
    
