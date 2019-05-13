@EmailSubscription
Feature: EmailSubscription Services Feature
Test the EmailSubscription Services
@API
Scenario: Test EmailSubscription Services API
Given I have below Details for EmailSubscription

|assetId                              | emailAddress   | emailSubject   | includeEventsFromChildassets | minSeverity | occurence | sendAsDigest |
|280302CC-5E1D-48A9-9CEC-8411A765B176 | avinash.singh@flsmidth.com | emailsubject |                          |                 |             |              |
When I post getAll delete getAll EmailSubscription
Then validate the EmailSubscription results