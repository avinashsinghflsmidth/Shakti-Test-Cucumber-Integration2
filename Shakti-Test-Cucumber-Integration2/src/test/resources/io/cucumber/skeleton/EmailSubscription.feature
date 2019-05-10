@EmailSubscription
Feature: EmailSubscription Services Feature
Test the EmailSubscription Services
@API
Scenario: Test EmailSubscription Services API
Given I have below Details for EmailSubscription

|assetId                              | emailAddress   | emailSubject   | includeEventsFromChildassets | minSeverity | occurence | sendAsDigest |
|1f163b17-ce8e-4fd4-ad62-04186aa8deb4 | auto@email.com | emailsubject |                          |                 |             |              |
When I post getAll delete getAll EmailSubscription
Then validate the EmailSubscription results